package com.example.spring_security_demotiepchonho.ra.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String name;
    private float price;
    private int quantity;
    private String description;
    private String image;
    private Long catalogId;
}
