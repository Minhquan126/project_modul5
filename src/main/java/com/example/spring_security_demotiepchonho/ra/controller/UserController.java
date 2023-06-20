package com.example.spring_security_demotiepchonho.ra.controller;

import com.example.spring_security_demotiepchonho.ra.dto.request.UserUpdateDTO;
import com.example.spring_security_demotiepchonho.ra.dto.response.ResponseMessage;
import com.example.spring_security_demotiepchonho.ra.model.Users;
import com.example.spring_security_demotiepchonho.ra.repository.UserRepository;
import com.example.spring_security_demotiepchonho.ra.service.serviceIMPL.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
@GetMapping
    public ResponseEntity<?> findAll(Pageable pageable){
    Page<Users> list = userService.getAll(pageable);
    if (list.isEmpty()){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
@PutMapping("block/{id}")
    public ResponseEntity<ResponseMessage> blockUser(@PathVariable("id") Long id){
    Users user = userService.findById(id);
if (user== null){
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
} else {
    userService.changeStatusUser(id);
    return new ResponseEntity<>(new ResponseMessage("block is success!"), HttpStatus.OK);
}
}
@PutMapping("password")
    public ResponseEntity<ResponseMessage> changePassword(@RequestParam("password") String password,
                                                          @RequestParam("userId") Long userId){
    Users user = userService.findById(userId);
    String newPass = passwordEncoder.encode(password);
    if (user.getPassword().equals(newPass)){
        return new ResponseEntity<>(new ResponseMessage("old password is match"),HttpStatus.BAD_REQUEST);
    }
    userService.changePassword(newPass,userId);
    return new ResponseEntity<>(new ResponseMessage("change password is success"), HttpStatus.OK);
}
@PutMapping("update")
    public ResponseEntity<Users> updateUser(@RequestBody UserUpdateDTO userUpdateDTO){
    Users user = userService.findById(userUpdateDTO.getId());
    if (user == null){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    user.setPhoneNumber(userUpdateDTO.getPhoneNumber());
    user.setAddress(userUpdateDTO.getAddress());
    user.setBirthday(userUpdateDTO.getBirthday());
    userService.save(user);
    return new ResponseEntity<>(user,HttpStatus.OK);
}
}
