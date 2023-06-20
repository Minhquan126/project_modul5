package com.example.spring_security_demotiepchonho.ra.service;

import com.example.spring_security_demotiepchonho.ra.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserService extends IGenericService<Users, Long>{
    Users findByUserName(String userName);
    boolean existByUserName(String userName);
    boolean existByEmail(String email);
    void changeStatusUser(Long id);
    void changePassword(String newPassword,Long userId);
    Page<Users> getAll(Pageable pageable);
}
