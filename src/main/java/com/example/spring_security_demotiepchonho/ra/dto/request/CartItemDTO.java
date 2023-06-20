package com.example.spring_security_demotiepchonho.ra.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private Long userId;
    private Long productId;
    private int quantity;
}
