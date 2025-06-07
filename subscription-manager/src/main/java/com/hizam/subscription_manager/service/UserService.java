package com.hizam.subscription_manager.service;

import com.hizam.subscription_manager.dto.UserInfoDto;
import com.hizam.subscription_manager.entity.User;
import com.hizam.subscription_manager.exception.RegistrationException;
import com.hizam.subscription_manager.repository.ReferenceTokenRepository;
import com.hizam.subscription_manager.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ReferenceTokenRepository referenceTokenRepository;

    private final ModelMapper mapper;

    public void saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {

            throw new RegistrationException("This email already exits");

        }

        userRepository.save(user);
    }


    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByEmail;
    }

    public void updateUserInfo(User user, HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();

        Arrays.stream(cookies).map(cookie ->
                        referenceTokenRepository.findByReferenceToken(cookie.getValue()))
                .filter(Objects::nonNull)
                .map(subscriberFound ->
                        userRepository.findById(subscriberFound.getUserId()))
                .filter(Optional::isPresent).
                forEach(subscriberFound -> {
                    subscriberFound.get().setEmail(user.getEmail());
                    subscriberFound.get().setFirstName(user.getFirstName());
                    subscriberFound.get().setLastName(user.getLastName());
                    userRepository.save(subscriberFound.get());
                });


    }

    public UserInfoDto getRegisteredUserInfoByEmail(String email)
    {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return mapper.map(user, UserInfoDto.class);
    }

    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userRepository.delete(user);
    }


    public List<UserInfoDto> getAllUsers()
    {
        List<User> users = userRepository.findAll();
        if(users.isEmpty())
        {
           throw new UsernameNotFoundException("User not found");
        }else {
            return users.stream().map(user -> mapper.map(user,UserInfoDto.class)).collect(Collectors.toList());
        }
    }





}
