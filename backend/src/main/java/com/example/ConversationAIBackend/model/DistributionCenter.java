package com.example.ConversationAIBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "distribution_centers")
public class DistributionCenter {
    @Id
    private Long id;

    private String name;
    private Double latitude;
    private Double longitude;
}

