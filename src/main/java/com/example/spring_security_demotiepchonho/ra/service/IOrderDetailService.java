package com.example.spring_security_demotiepchonho.ra.service;

import com.example.spring_security_demotiepchonho.ra.model.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderDetailService extends IGenericService<OrderDetail,Long> {
    Page<OrderDetail> getAll(Pageable pageable);
    List<OrderDetail> getAllByUserId(Long id);
    List<OrderDetail> getAllByOrderId(Long id);
    float sum();
}
