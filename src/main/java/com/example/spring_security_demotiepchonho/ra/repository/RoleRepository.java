package com.example.spring_security_demotiepchonho.ra.repository;

import com.example.spring_security_demotiepchonho.ra.model.RoleName;
import com.example.spring_security_demotiepchonho.ra.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Long> {

    Optional<Roles> findByRoleName(RoleName name);
}
