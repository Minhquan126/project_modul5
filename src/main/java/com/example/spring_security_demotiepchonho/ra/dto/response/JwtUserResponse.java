package com.example.spring_security_demotiepchonho.ra.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUserResponse {
    private String userName;
    private String email;
    private String token;
    private String type = "Bearer";
    private List<String> listRoles;


}
