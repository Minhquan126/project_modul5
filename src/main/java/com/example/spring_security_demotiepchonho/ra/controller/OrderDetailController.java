package com.example.spring_security_demotiepchonho.ra.controller;


import com.example.spring_security_demotiepchonho.ra.dto.request.OrderDetailDTO;
import com.example.spring_security_demotiepchonho.ra.dto.response.ResponseMessage;
import com.example.spring_security_demotiepchonho.ra.model.CartItem;
import com.example.spring_security_demotiepchonho.ra.model.OrderDetail;
import com.example.spring_security_demotiepchonho.ra.model.Product;
import com.example.spring_security_demotiepchonho.ra.model.Users;
import com.example.spring_security_demotiepchonho.ra.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4/orderDetails")
public class OrderDetailController {
    @Autowired
    IOrderDetailService orderDetailService;
    @Autowired
    IUserService userService;
    @Autowired
    IProductService productService;
    @Autowired
    ICartItemService cartItemService;
    @Autowired
    IOrdersService ordersService;

@GetMapping
public ResponseEntity<?> getAll(Pageable pageable){
    Page<OrderDetail> list = orderDetailService.getAll(pageable);
    return new ResponseEntity<>(list,HttpStatus.OK);
}
    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderDetail>> getAllByUser(@PathVariable("userId") Long userId) {
        List<OrderDetail> list = orderDetailService.getAllByUserId(userId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @PostMapping("create")
    public ResponseEntity<ResponseMessage> create(@RequestBody OrderDetailDTO orderDetailDTO) {
        Users user = userService.findById(orderDetailDTO.getUserId());
        Product product = productService.findById(orderDetailDTO.getProductId());
        CartItem cartItem = cartItemService.findByUserAndProduct(user,product).get();
        OrderDetail orderDetail = OrderDetail.builder().
                orders(ordersService.getOrdersByDateBuy(orderDetailDTO.getUserId())).
                product(cartItem.getProduct()).
                productName(cartItem.getProductName()).
                price(cartItem.getPrice()).
                quantity(cartItem.getQuantity())
                .build();
        orderDetailService.save(orderDetail);
        productService.setQuantityProduct(cartItem.getQuantity(), orderDetailDTO.getProductId());
        ordersService.setTotal(orderDetailService.sum(),ordersService.getOrdersByDateBuy(orderDetailDTO.getUserId()).getId());
        productService.updateStar(orderDetailDTO.getProductId());
        cartItemService.deleteAllByUserId(orderDetailDTO.getUserId());

        return new ResponseEntity<>(new ResponseMessage("create is success"), HttpStatus.OK);
    }

}
