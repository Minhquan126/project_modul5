package com.example.spring_security_demotiepchonho.ra.service.serviceIMPL;

import com.example.spring_security_demotiepchonho.ra.model.RoleName;
import com.example.spring_security_demotiepchonho.ra.model.Roles;
import com.example.spring_security_demotiepchonho.ra.repository.RoleRepository;
import com.example.spring_security_demotiepchonho.ra.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleService implements IRoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Iterable<Roles> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public boolean save(Roles roles) {
        roleRepository.save(roles);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        roleRepository.deleteById(id);
        return true;
    }

    @Override
    public Roles findById(Long aLong) {
        return roleRepository.findById(aLong).get();
    }

    @Override
    public Roles findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName).get();
    }
}
