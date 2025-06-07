package com.hizam.subscription_manager.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfoDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String role;
}
