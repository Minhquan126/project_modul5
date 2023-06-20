package com.example.spring_security_demotiepchonho.ra.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    private Long id;
    private String phoneNumber;
    private String address;
    private Date birthday;

}
