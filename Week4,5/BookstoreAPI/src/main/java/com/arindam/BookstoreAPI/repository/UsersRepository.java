package com.arindam.BookstoreAPI.repository;

import com.arindam.BookstoreAPI.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
