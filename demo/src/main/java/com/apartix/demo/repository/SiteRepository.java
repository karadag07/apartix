package com.apartix.demo.repository;

import com.apartix.demo.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteRepository extends JpaRepository<Site, Integer> {

    Optional<Site> findByJoinCode(String joinCode);

}