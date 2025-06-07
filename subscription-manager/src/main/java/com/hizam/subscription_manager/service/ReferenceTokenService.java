package com.hizam.subscription_manager.service;

import com.hizam.subscription_manager.entity.ReferenceToken;
import com.hizam.subscription_manager.repository.ReferenceTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReferenceTokenService {
    private final ReferenceTokenRepository referenceTokenRepository;


    public ReferenceToken generateReferenceTokenById(Long userId)
    {
        var referenceToken = ReferenceToken.builder()
                .referenceToken(UUID.randomUUID().toString())
                .userId(userId)
                .build();
        referenceTokenRepository.save(referenceToken);
        return referenceToken;
    }
}
