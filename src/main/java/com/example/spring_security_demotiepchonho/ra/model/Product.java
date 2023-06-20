package com.example.spring_security_demotiepchonho.ra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",unique = true,nullable = false)
    private String name;
    @Column(name = "price",nullable = false)
@Min(0)
    private float price;
    @Column(name = "quantity",nullable = false)
    @Min(0)
    private int quantity;
    @Column(name = "description",nullable = false)
    private String description;
    @Column(name = "image",nullable = false)
    private String image;
    @ManyToOne
    @JoinColumn(name = "catalogId")

    private Catalog catalog;

    private boolean status = true;
    @Column(name = "star")
    @Min(0)
    private int star = 0;

    private boolean type = true;
    @OneToMany(mappedBy = "product",targetEntity = OrderDetail.class)
    @JsonIgnore
    private List<OrderDetail> orderDetails;
    @OneToMany(mappedBy = "product", targetEntity = CartItem.class)
    @JsonIgnore
    private List<CartItem> cartItems;
}
