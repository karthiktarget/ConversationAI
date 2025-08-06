package com.example.ConversationAIBackend.repository;

import com.example.ConversationAIBackend.model.DistributionCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionCenterRepository extends JpaRepository<DistributionCenter, Long> {}

