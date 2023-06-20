package com.example.spring_security_demotiepchonho.ra.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

public class CatalogDTO {
    private String name;
    private String description;
    private String image;
    private String country;

    public CatalogDTO() {
    }

    public CatalogDTO(String name, String description, String image, String country) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
