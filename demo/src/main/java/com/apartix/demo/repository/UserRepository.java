package com.apartix.demo.repository;

import com.apartix.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    boolean existsBySiteIdAndApartmentNo(Integer siteId, String apartmentNo);

}