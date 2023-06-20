package com.example.spring_security_demotiepchonho.ra.repository;

import com.example.spring_security_demotiepchonho.ra.model.CartItem;
import com.example.spring_security_demotiepchonho.ra.model.Product;
import com.example.spring_security_demotiepchonho.ra.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query(value = "select * from CartItem where userId = ?1", nativeQuery = true)
    List<CartItem> getAllByUser(Long userId);
    List<CartItem> getCartItemsByUserId(Long userId);
    void deleteCartItemByUserAndProduct(Users users,Product product);

    @Query(value = "delete from CartItem where productId = ?1", nativeQuery = true)
    void deleteCartItemByProductId(Long id);

    @Modifying
    @Query(value = "delete from CartItem where userId = ?1", nativeQuery = true)
    void deleteAllByUserId(Long userid);


    Optional<CartItem> findByUserAndProduct(Users u, Product p);
}
