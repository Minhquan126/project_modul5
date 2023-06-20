package com.example.spring_security_demotiepchonho.ra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",unique = true,nullable = false)
    private String name;
    @Column(name = "description",nullable = false)
    private String description;
    @Column(name = "country",nullable = false)
    private String country;
    @Column(name = "image",nullable = false,unique = true)
    private String image;

    private boolean status = true;
    @OneToMany(mappedBy = "catalog",targetEntity = Product.class)
    @JsonIgnore
    private Set<Product> products ;
}
