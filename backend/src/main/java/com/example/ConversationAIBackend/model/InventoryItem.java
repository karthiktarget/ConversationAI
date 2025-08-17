package com.example.ConversationAIBackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_items")
@Data
public class InventoryItem {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime createdAt;
    private LocalDateTime soldAt;
    private Double cost;

    private String productCategory;
    private String productName;
    private String productBrand;
    private Double productRetailPrice;
    private String productDepartment;
    private String productSku;

    @ManyToOne
    @JoinColumn(name = "product_distribution_center_id")
    private DistributionCenter productDistributionCenter;
}
