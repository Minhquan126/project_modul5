package com.example.spring_security_demotiepchonho.ra.dto.request;

import com.example.spring_security_demotiepchonho.ra.model.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
   private Long userId;
   private Long productId;

}
