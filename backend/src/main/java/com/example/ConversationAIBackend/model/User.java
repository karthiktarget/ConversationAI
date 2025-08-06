package com.example.ConversationAIBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String gender;
    private String state;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String country;
    private Double latitude;
    private Double longitude;
    private String trafficSource;
    private LocalDateTime createdAt;
}

