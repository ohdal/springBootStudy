package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // findFirstBy -> 가장최근 한건
    // OrderByIdDesc -> ID 역순으로
    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
}
