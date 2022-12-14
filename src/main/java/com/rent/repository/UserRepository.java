package com.rent.repository;

import com.rent.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String Login);
    void deleteById(Long id);
}
