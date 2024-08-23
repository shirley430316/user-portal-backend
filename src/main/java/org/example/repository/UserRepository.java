package org.example.repository;

import org.example.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{
    // select pwd from user where user_email = :email limit 1
    String findPwdByEmail(String email);

}
