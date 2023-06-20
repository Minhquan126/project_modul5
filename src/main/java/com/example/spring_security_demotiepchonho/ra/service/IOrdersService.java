package com.example.spring_security_demotiepchonho.ra.service;

import com.example.spring_security_demotiepchonho.ra.model.Orders;

import java.util.Date;

public interface IOrdersService extends IGenericService<Orders, Long>{
    int getCountTrue();
    int getCountFalse();
    void order(Long id);
    float getTotal();
    float getTotalByMonth(Date date);
    float getTotalThisMonth();
    Orders getOrdersByDateBuy(Long id);
    void setTotal(float total, Long id);
}
