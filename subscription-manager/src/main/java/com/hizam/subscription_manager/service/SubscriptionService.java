package com.hizam.subscription_manager.service;

import com.hizam.subscription_manager.entity.ReferenceToken;
import com.hizam.subscription_manager.entity.Subscription;
import com.hizam.subscription_manager.exception.SubscriptionException;
import com.hizam.subscription_manager.repository.ReferenceTokenRepository;
import com.hizam.subscription_manager.repository.SubscriptionRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final ReferenceTokenRepository referenceTokenRepository;


    public void addDigitalService(String digitalServiceName) {
        Subscription subscription = Subscription.builder()
                .digitalServiceName(digitalServiceName).build();
        if (subscriptionRepository.existsByDigitalServiceName(digitalServiceName)) {
            log.error("This DigitalService already exists");
            throw new SubscriptionException("This DigitalService already exists");
        }
        subscriptionRepository.save(subscription);
        log.info("DigitalService {} has been successfully added",digitalServiceName);
    }


    public void subscribe(String digitalServiceName, HttpServletRequest request) {
        Subscription subscriptionFound = subscriptionRepository.findByDigitalServiceName(digitalServiceName);
        if (subscriptionFound != null) {
            Cookie[] cookie = request.getCookies();
            ReferenceToken referenceToken = referenceTokenRepository.findByReferenceToken(cookie[1].getValue());
            subscriptionFound.setSubscriberId(referenceToken.getUserId());
            subscriptionRepository.save(subscriptionFound);
            log.info("You have successfully subscribed to {}",subscriptionFound.getDigitalServiceName());
        } else {
            log.error("There is no digitalService with the name {}", digitalServiceName);
            throw new SubscriptionException("There is no digitalService with the name " + digitalServiceName);
        }
    }

    public List<String> getSubscriptionList(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        ReferenceToken tokenReference = referenceTokenRepository.findByReferenceToken(cookies[1].getValue());
        List<String> subscriptionList = subscriptionRepository.findServicesBySubscriberId(tokenReference.getUserId());
        log.info("Your subscriptionList are {}", subscriptionList);
       return subscriptionList;
    }

    @Transactional
    public void unsubscribe(String digitalServiceName, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        ReferenceToken tokenReference = referenceTokenRepository.findByReferenceToken(cookies[1].getValue());
        Optional<Subscription> subscriptionFound = subscriptionRepository.findById(tokenReference.getUserId());
        if (subscriptionFound.isPresent() && subscriptionFound.get().getDigitalServiceName().equals(digitalServiceName)) {
            subscriptionRepository.updateSubscriberIdByDigitalServiceName(subscriptionFound.get().getSubscriberId());
            log.info("You have been unsubscribed from {}",subscriptionFound.get().getDigitalServiceName());
        }
        log.error("You are unsubscribing from a digitalService that doesn't exist");
        throw new SubscriptionException("You are unsubscribing from a digitalService that doesn't exist");
    }


    @Transactional
    public void unsubscribeFromAllDigitalServices(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        ReferenceToken referenceToken = referenceTokenRepository.findByReferenceToken(cookies[1].getValue());
        Optional<Subscription> subscriptionFound = subscriptionRepository.findById(referenceToken.getUserId());
        if(subscriptionFound.isPresent()) {
            subscriptionRepository.updateSubscriberIdByDigitalServiceName(subscriptionFound.get().getSubscriberId());
            log.info("You have unsubscribed from all the digitalServices");
        }
        log.error("You haven't subscribed to any digitalService yet");
        throw new SubscriptionException("You haven't subscribed to any digitalService yet");
    }


}
