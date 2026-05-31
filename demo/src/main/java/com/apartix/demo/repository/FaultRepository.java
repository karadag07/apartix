package com.apartix.demo.repository;

import com.apartix.demo.entity.Fault;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaultRepository extends JpaRepository<Fault, Integer> {

    List<Fault> findBySiteIdOrderByCreatedAtDesc(Integer siteId);

}