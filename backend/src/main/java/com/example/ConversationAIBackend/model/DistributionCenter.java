package com.example.ConversationAIBackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "distribution_centers")
@Data
public class DistributionCenter {
    @Id
    private Long id;

    private String name;
    private Double latitude;
    private Double longitude;
}
