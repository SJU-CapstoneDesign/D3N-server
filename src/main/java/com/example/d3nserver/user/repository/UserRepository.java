package com.example.d3nserver.user.repository;

import com.example.d3nserver.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findUserById(Long id);



}
