package com.example.spring_security_demotiepchonho.ra.service.serviceIMPL;

import com.example.spring_security_demotiepchonho.ra.model.OrderDetail;
import com.example.spring_security_demotiepchonho.ra.repository.OrderDetailRepository;
import com.example.spring_security_demotiepchonho.ra.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDetailService implements IOrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public Iterable<OrderDetail> findAll() {
        return null;
    }

    @Override
    public boolean save(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
        return true;
    }

    @Override
    public boolean delete(Long aLong) {
        orderDetailRepository.deleteById(aLong);
        return true;
    }

    @Override
    public OrderDetail findById(Long aLong) {
        return null;
    }

    @Override
    public Page<OrderDetail> getAll(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 10);
        return orderDetailRepository.getAll(pageable);
    }

    @Override
    public List<OrderDetail> getAllByUserId(Long id) {
        return orderDetailRepository.getAllByUserId(id);
    }

    @Override
    public List<OrderDetail> getAllByOrderId(Long id) {
        return orderDetailRepository.getAllByOrderId(id);
    }

    @Override
    public float sum() {
        return orderDetailRepository.sum();
    }


}
