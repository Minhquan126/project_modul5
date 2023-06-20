package com.example.spring_security_demotiepchonho.ra.service.serviceIMPL;

import com.example.spring_security_demotiepchonho.ra.model.Users;
import com.example.spring_security_demotiepchonho.ra.repository.UserRepository;
import com.example.spring_security_demotiepchonho.ra.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public Iterable<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean save(Users users) {
        userRepository.save(users);
        return true;
    }

    @Override
    public boolean delete(Long aLong) {
        return false;
    }

    @Override
    public Users findById(Long aLong) {

        return userRepository.findById(aLong).get();
    }

    @Override
    public Users findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public boolean existByUserName(String userName) {

        return userRepository.existsByUserName(userName);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void changeStatusUser(Long id) {
        userRepository.changeStatusUser(id);
    }

    @Override
    public void changePassword(String newPassword, Long userId) {
            userRepository.changePassword(newPassword,userId);
    }

    @Override
    public Page<Users> getAll(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 10);
        return userRepository.getAll(pageable);
    }
}
