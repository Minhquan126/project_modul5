package com.example.spring_security_demotiepchonho.ra.controller;

import com.example.spring_security_demotiepchonho.ra.dto.request.CartItemDTO;
import com.example.spring_security_demotiepchonho.ra.dto.response.ResponseMessage;
import com.example.spring_security_demotiepchonho.ra.model.CartItem;
import com.example.spring_security_demotiepchonho.ra.model.Product;
import com.example.spring_security_demotiepchonho.ra.model.Users;
import com.example.spring_security_demotiepchonho.ra.service.ICartItemService;
import com.example.spring_security_demotiepchonho.ra.service.IProductService;
import com.example.spring_security_demotiepchonho.ra.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4/cartItems")
public class CartItemController {
    @Autowired
    ICartItemService cartItemService;
    @Autowired
    IUserService userService;
    @Autowired
    IProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<List<CartItem>> getCartItemByUser(@PathVariable("id") Long id) {
        List<CartItem> list = cartItemService.getCartItemsByUserId(id);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @PostMapping("create")
    public ResponseEntity<ResponseMessage> create(@RequestBody CartItemDTO cartItemDTO) {
        Users users = userService.findById(cartItemDTO.getUserId());
        Product product = productService.findById(cartItemDTO.getProductId());
        Optional<CartItem> cartItemOptional = cartItemService.findByUserAndProduct(users, product);
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity() + cartItemDTO.getQuantity());
            cartItemService.save(cartItem);
        } else {
            CartItem cartItem = CartItem.builder().
                    user(userService.findById(cartItemDTO.getUserId())).
                    product(productService.findById(cartItemDTO.getProductId())).
                    productName(productService.findById(cartItemDTO.getProductId()).getName()).
                    price(productService.findById(cartItemDTO.getProductId()).getPrice()).
                    quantity(cartItemDTO.getQuantity()).
                    build();
            cartItemService.save(cartItem);
        }
        return new ResponseEntity<>(new ResponseMessage("create is success!"), HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<ResponseMessage> deleteByProductId(@RequestParam("userId") Long userId,@RequestParam("productId") Long productId) {
        Users user = userService.findById(userId);
        Product product = productService.findById(productId);
        cartItemService.deleteCartItemByUserAndProduct(user,product);
        return new ResponseEntity<>(new ResponseMessage("delete is success!"), HttpStatus.OK);
    }
}
