package com.example.spring_security_demotiepchonho.ra.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormLogin {
    @NotBlank
    private String userName;
    @NotBlank
    @Size(min = 6,max = 12)
    private String password;
}
