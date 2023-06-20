package com.example.spring_security_demotiepchonho.ra.repository;

import com.example.spring_security_demotiepchonho.ra.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    @Query("select u from Users as u")
    Page<Users> getAll(Pageable pageable);
    @Modifying
    @Query(value = "update User u set u.status = !u.status where u.id = ?1", nativeQuery = true)
    void changeStatusUser(Long id);
    @Modifying
    @Query("update Users u set u.password = ?1 where u.id = ?2")
    void changePassword(String newPassword,Long userId);

}
