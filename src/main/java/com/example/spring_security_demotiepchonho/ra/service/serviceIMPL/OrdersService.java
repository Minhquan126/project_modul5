package com.example.spring_security_demotiepchonho.ra.service.serviceIMPL;

import com.example.spring_security_demotiepchonho.ra.model.OrderDetail;
import com.example.spring_security_demotiepchonho.ra.model.Orders;
import com.example.spring_security_demotiepchonho.ra.repository.OrderDetailRepository;
import com.example.spring_security_demotiepchonho.ra.repository.OrderRepository;
import com.example.spring_security_demotiepchonho.ra.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdersService implements IOrdersService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public Iterable<Orders> findAll() {
        return null;
    }

    @Override
    public boolean save(Orders orders) {
        orderRepository.save(orders);
        return true;
    }

    @Override
    public boolean delete(Long aLong) {
        return false;
    }

    @Override
    public Orders findById(Long aLong) {
        return orderRepository.findById(aLong).get();
    }


    @Override
    public int getCountTrue() {
        return orderRepository.getCountTrue();
    }

    @Override
    public int getCountFalse() {
        return orderRepository.getCountFalse();
    }

    @Override
    public void order(Long id) {
orderRepository.order(id);
    }

    @Override
    public float getTotal() {
        return orderRepository.getTotal();
    }

    @Override
    public float getTotalByMonth(Date date) {
        return orderRepository.getTotalByMonth(date);
    }

    @Override
    public float getTotalThisMonth() {
        return orderRepository.getTotalThisMonth();
    }

    @Override
    public Orders getOrdersByDateBuy(Long id) {
        return orderRepository.getOrdersByDateBuy(id);
    }

    @Override
    public void setTotal(float total, Long id) {
        orderRepository.setTotal(total,id);
    }
}
