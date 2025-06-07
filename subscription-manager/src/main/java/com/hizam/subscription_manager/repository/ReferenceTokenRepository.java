package com.hizam.subscription_manager.repository;

import com.hizam.subscription_manager.entity.ReferenceToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceTokenRepository extends JpaRepository<ReferenceToken,Long> {
    ReferenceToken findByReferenceToken(String referenceToken);
}
