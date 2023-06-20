package com.example.spring_security_demotiepchonho.ra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "userId")
    @JsonIgnore
    private Users user;
    @DateTimeFormat(pattern = "yyyy-MM-dd") private String dateBuy ;
    private float total;
    private boolean status;
    @OneToMany(mappedBy = "orders",targetEntity = OrderDetail.class)
    private List<OrderDetail> orderDetails;
}
