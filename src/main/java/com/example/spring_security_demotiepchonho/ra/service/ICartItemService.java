package com.example.spring_security_demotiepchonho.ra.service;

import com.example.spring_security_demotiepchonho.ra.model.CartItem;
import com.example.spring_security_demotiepchonho.ra.model.Product;
import com.example.spring_security_demotiepchonho.ra.model.Users;
import com.example.spring_security_demotiepchonho.ra.service.IGenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICartItemService extends IGenericService<CartItem, Long> {
    List<CartItem> getAllByUser(Long userId);
    void deleteAllByUserId(Long userId);
    void deleteCartItemByProductId(Long id);
    Optional<CartItem> findByUserAndProduct(Users u, Product p);
    List<CartItem> getCartItemsByUserId(Long userId);
    void deleteCartItemByUserAndProduct(Users users,Product product);
}
