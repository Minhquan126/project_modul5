package com.example.spring_security_demotiepchonho.ra.controller;

import com.example.spring_security_demotiepchonho.ra.dto.request.FormLogin;
import com.example.spring_security_demotiepchonho.ra.dto.request.FormRegister;
import com.example.spring_security_demotiepchonho.ra.dto.response.ErrorMessage;
import com.example.spring_security_demotiepchonho.ra.dto.response.JwtUserResponse;
import com.example.spring_security_demotiepchonho.ra.dto.response.ResponseMessage;
import com.example.spring_security_demotiepchonho.ra.model.RoleName;
import com.example.spring_security_demotiepchonho.ra.model.Roles;
import com.example.spring_security_demotiepchonho.ra.model.Users;
import com.example.spring_security_demotiepchonho.ra.security.jwt.JwtTokenProvider;
import com.example.spring_security_demotiepchonho.ra.security.userPrincipal.CustomUserDetail;
import com.example.spring_security_demotiepchonho.ra.service.serviceIMPL.RoleService;
import com.example.spring_security_demotiepchonho.ra.service.serviceIMPL.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signIn")
    public ResponseEntity<?> login(@Valid @RequestBody FormLogin formLogin,BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            List<FieldError> list = bindingResult.getFieldErrors();
            List<String> message = new ArrayList<>();
            for (FieldError e:list) {
                message.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
            }
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(formLogin.getUserName(), formLogin.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
        String token = tokenProvider.createToken(userDetail);
        List<String> roles = userDetail.getAuthorities().stream().map(
                role -> role.getAuthority()
        ).collect(Collectors.toList());
        JwtUserResponse response = new JwtUserResponse(userDetail.getUsername(),
                userDetail.getEmail(),token,"Bearer",roles);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> register(@Valid @RequestBody FormRegister formRegister, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            List<FieldError> list = bindingResult.getFieldErrors();
            List<String> message= new ArrayList<>();
            for (FieldError e : list){
                message.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
            }
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
        if (userService.existByUserName(formRegister.getUserName())) {
            return ResponseEntity.ok(new ResponseMessage("UserName is existed!"));
        }
        if (userService.existByEmail(formRegister.getEmail())) {
            return ResponseEntity.ok(new ResponseMessage("email is existed!"));
        }
        Set<String> roles = formRegister.getRoles();
        Set<Roles> listRoles = new HashSet<>();
        if (roles== null||roles.isEmpty()){
            listRoles.add(roleService.findByRoleName(RoleName.USER));
        }else {
            roles.forEach(role->{
                switch (role){
                    case "admin":
                        listRoles.add(roleService.findByRoleName(RoleName.ADMIN));
                    case "pm":
                        listRoles.add(roleService.findByRoleName(RoleName.PM));
                    case "user":
                        listRoles.add(roleService.findByRoleName(RoleName.USER));
                }
            });
        }
        Users user = Users.builder().
                userName(formRegister.getUserName()).
                email(formRegister.getEmail()).
                password(passwordEncoder.encode(formRegister.getPassword())).
                roles(listRoles).
                status(true).
                build();

        userService.save(user);
        return ResponseEntity.ok(new ResponseMessage("Register success"));
    }
}