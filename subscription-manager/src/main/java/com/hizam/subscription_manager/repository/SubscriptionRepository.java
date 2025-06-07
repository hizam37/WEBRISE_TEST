package com.hizam.subscription_manager.repository;

import com.hizam.subscription_manager.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {


    @Query("SELECT S.digitalServiceName FROM Subscription S WHERE S.subscriberId=:subscriberId")
    List<String> findServicesBySubscriberId(Long subscriberId);

    boolean existsByDigitalServiceName(String digitalServiceName);

    @Modifying
    @Query("UPDATE Subscription S SET S.subscriberId = null WHERE S.subscriberId =:subscriberId ")
    void updateSubscriberIdByDigitalServiceName(@Param("subscriberId") Long subscriberId);

    void deleteAllById(Long id);


    Subscription findByDigitalServiceName(String digitalServiceName);
}
