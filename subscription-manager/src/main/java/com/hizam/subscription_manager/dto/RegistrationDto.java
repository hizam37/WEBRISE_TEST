package com.hizam.subscription_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private String email;
    private String role;
}
