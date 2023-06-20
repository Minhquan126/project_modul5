package com.example.spring_security_demotiepchonho.ra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "userId")
    private Users user;
    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "productId")
    private Product product;
    private String productName;
    private float price;
    private int quantity;
}
