package com.itransition.lobach.simpleuserapp.repository;

import com.itransition.lobach.simpleuserapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    void deleteUserByUsername(String username);
    List<User> findAll();
}