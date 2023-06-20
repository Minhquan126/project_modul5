package com.example.spring_security_demotiepchonho.ra.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormRegister {
    @NotBlank
    private String userName;
    @NotBlank
    @Email
    private String email;
    @Size(min = 6,max = 12)
    @NotBlank
    private String password;
    private Set<String> roles;
}
