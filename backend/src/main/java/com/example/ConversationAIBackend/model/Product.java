package com.example.ConversationAIBackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    private Long id;

    private Double cost;
    private String category;
    private String name;
    private String brand;
    private Double retailPrice;
    private String department;
    private String sku;

    @ManyToOne
    @JoinColumn(name = "distribution_center_id")
    private DistributionCenter distributionCenter;
}
