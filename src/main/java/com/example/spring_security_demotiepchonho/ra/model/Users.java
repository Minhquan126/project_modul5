package com.example.spring_security_demotiepchonho.ra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "userName",unique = true)
    @NotBlank
    private String userName;
    @Column(name = "email",unique = true)
    @Email
    @NotBlank
    private String email;   
    @Size(min = 6)
    @JsonIgnore
    @NotBlank
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @Size(min = 9,max = 11)
    private String phoneNumber;
    private String address;
    private boolean status = true;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "userId"),
    inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Roles> roles = new HashSet<>();
    @OneToMany(mappedBy = "user",targetEntity = Orders.class)
    private List<Orders> orders;
    @OneToMany(mappedBy = "user",targetEntity = CartItem.class)
    private List<CartItem> cartItems;
    public Users(String userName, String email, String password, Set<Roles> roles) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
