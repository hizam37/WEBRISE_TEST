package com.hizam.subscription_manager.dto;

import com.hizam.subscription_manager.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDto {

    private Long id;

    private String digitalServiceName;

    private Long subscriberId;

}
