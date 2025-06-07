package com.hizam.subscription_manager.service;

import com.hizam.subscription_manager.dto.RegistrationDto;
import com.hizam.subscription_manager.dto.SignInDto;
import com.hizam.subscription_manager.entity.ReferenceToken;
import com.hizam.subscription_manager.entity.Role;
import com.hizam.subscription_manager.entity.User;
import com.hizam.subscription_manager.security.security.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {


    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ReferenceTokenService referenceTokenService;
    private final PasswordEncoder passwordEncoder;
    public void register(RegistrationDto user)
    {
        User userToBeCreated = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(passwordEncoder.encode(user.getPassword()))
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .role(Role.valueOf(user.getRole()))
                .build();
        userService.saveUser(userToBeCreated);
        log.info("User {} has been successfully created",user.getFirstName());
    }





    public void login(SignInDto signInRequest, HttpServletResponse response)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));
        var user = userService.getByEmail(signInRequest.getEmail());
        var jwt = jwtService.generateToken(user);
        Cookie jwtCookie  = new Cookie("accessToken",jwt);
        ReferenceToken referenceToken = referenceTokenService.generateReferenceTokenById(user.getId());
        Cookie referenceTokenCookie = new Cookie("referenceToken",referenceToken.getReferenceToken());
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        referenceTokenCookie.setHttpOnly(true);
        referenceTokenCookie.setSecure(true);
        response.addCookie(jwtCookie);
        response.addCookie(referenceTokenCookie);
        log.info("User {} has been successfully logged in",user.getFirstName());
    }


}

