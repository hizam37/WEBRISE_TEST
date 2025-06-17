package com.hizam.subscription_manager.controller;

import com.hizam.subscription_manager.dto.UserInfoDto;
import com.hizam.subscription_manager.entity.User;
import com.hizam.subscription_manager.service.SubscriptionService;
import com.hizam.subscription_manager.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/login")
public class SubController {
    private final SubscriptionService subscriptionService;

    private final UserService userService;



    @PostMapping("/add_sub_service/{digitalServiceName}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public void addSubService(@PathVariable String digitalServiceName) {
        subscriptionService.addDigitalService(digitalServiceName);
    }

    @PutMapping("/edit_my_info")
    @PreAuthorize("hasRole('ROLE_SUBSCRIBER')")
    public void editUserInfo(@RequestBody User user, HttpServletRequest request) {
        userService.updateUserInfo(user, request);
    }

    @PostMapping("/subscribe/{digitalServiceName}")
    @PreAuthorize("hasRole('ROLE_SUBSCRIBER')")
    public void subscribe(@PathVariable String digitalServiceName, HttpServletRequest request) {
        subscriptionService.subscribe(digitalServiceName, request);
    }


    @GetMapping("/display_my_subscribed_services")
    @PreAuthorize("hasRole('ROLE_SUBSCRIBER')")
    public List<String> displaySubscribedServices(HttpServletRequest request) {
        return subscriptionService.getSubscriptionList(request);
    }


    @GetMapping("/view_user_info_by_email/{email}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public UserInfoDto getUserInfoByEmail(@PathVariable String email) {
        return userService.getRegisteredUserInfoByEmail(email);
    }



    @DeleteMapping("/delete_by_email/{email}")
    @PreAuthorize("hasRole('ROLE_SUBSCRIBER')")
    public void deleteByEmail(@PathVariable String email) {
        userService.deleteUserByEmail(email);
    }


    @PutMapping("/unsubscribe/{service_name}")
    @PreAuthorize("hasRole('ROLE_SUBSCRIBER')")
    public void unsubscribe(@PathVariable String service_name,HttpServletRequest request)
    {
        subscriptionService.unsubscribe(service_name,request);
    }

    @PutMapping("/unsubscribe_from_all_services")
    @PreAuthorize("hasRole('ROLE_SUBSCRIBER')")
    public void unsubscribeFromAllServices(HttpServletRequest request)
    {
        subscriptionService.unsubscribeFromAllDigitalServices(request);
    }


}
