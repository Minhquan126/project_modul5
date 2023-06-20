package com.example.spring_security_demotiepchonho.ra.controller;

import com.example.spring_security_demotiepchonho.ra.dto.request.OrderDTO;
import com.example.spring_security_demotiepchonho.ra.dto.response.ResponseMessage;
import com.example.spring_security_demotiepchonho.ra.model.Orders;
import com.example.spring_security_demotiepchonho.ra.service.IOrdersService;
import com.example.spring_security_demotiepchonho.ra.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4/orders")
public class OrderController {
    @Autowired
    IOrdersService ordersService;
    @Autowired
    IUserService userService;

    @PostMapping
    public ResponseEntity<ResponseMessage> create(@RequestBody OrderDTO orderDTO) {
        Orders order = Orders.builder().
                user(userService.findById(orderDTO.getUserId())).
                dateBuy(LocalDateTime.now().toString()).
                build();
        ordersService.save(order);
        return new ResponseEntity<>(new ResponseMessage("create is success!"), HttpStatus.OK);
    }

    @PutMapping("checkOrder/{id}")
    public ResponseEntity<ResponseMessage> orders(@PathVariable("id") Long id) {
        ordersService.order(id);
        return new ResponseEntity<>(new ResponseMessage("order is complete"), HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<?> getTotal() {
        float total = ordersService.getTotal();
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
@GetMapping("/totalByMonth")
    public ResponseEntity<?> getTotalByMonth(@RequestParam("date") Date date){
        float total = ordersService.getTotalByMonth(date);
        return new ResponseEntity<>(total,HttpStatus.OK);
}
@GetMapping("/totalThisMonth")
    public ResponseEntity<?> getTotalThisMonth(){
        float total = ordersService.getTotalThisMonth();
        return new ResponseEntity<>(total,HttpStatus.OK);
}
@GetMapping("/numberOfOrders")
    public ResponseEntity<?> getNumberOrders(){
        int number = ordersService.getCountTrue();
        return new ResponseEntity<>(number,HttpStatus.OK);
}
@GetMapping("/numberOfOrderFalse")
    public ResponseEntity<?> getNumberOrdersFalse(){
        int number = ordersService.getCountFalse();
        return new ResponseEntity<>(number,HttpStatus.OK);
}
}
