package com.example.spring_security_demotiepchonho.ra.service.serviceIMPL;

import com.example.spring_security_demotiepchonho.ra.model.CartItem;
import com.example.spring_security_demotiepchonho.ra.model.Product;
import com.example.spring_security_demotiepchonho.ra.model.Users;
import com.example.spring_security_demotiepchonho.ra.repository.CartItemRepository;
import com.example.spring_security_demotiepchonho.ra.service.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService implements ICartItemService {
    @Autowired
    CartItemRepository cartItemRepository;
//    @Override
//    public Page<CartItem> ge(Pageable pageable, Sort sort) {
//        pageable = PageRequest.of(pageable.getPageNumber(), 10,sort);
//        return cartItemRepository.findAll(pageable);
//    }

    @Override
    public Iterable<CartItem> findAll() {
        return null;
    }

    @Override
    public boolean save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
        return true;
    }

    @Override
    public boolean delete(Long aLong) {
        cartItemRepository.deleteById(aLong);
        return true;
    }

    @Override
    public CartItem findById(Long aLong) {
        return null;
    }

    @Override
    public List<CartItem> getAllByUser(Long userId) {
        return cartItemRepository.getAllByUser(userId);
    }



    @Override
    public void deleteAllByUserId(Long userId) {
       cartItemRepository.deleteAllByUserId(userId);
    }

    @Override
    public void deleteCartItemByProductId(Long id) {
        cartItemRepository.deleteCartItemByProductId(id);
    }

    @Override
    public Optional<CartItem> findByUserAndProduct(Users u, Product p) {
        return cartItemRepository.findByUserAndProduct(u,p);
    }

    @Override
    public List<CartItem> getCartItemsByUserId(Long userId) {
        return cartItemRepository.getCartItemsByUserId(userId);
    }

    @Override
    public void deleteCartItemByUserAndProduct(Users users, Product product) {
        cartItemRepository.deleteCartItemByUserAndProduct(users,product);
    }
}
