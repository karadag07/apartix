package com.apartix.demo.repository;

import com.apartix.demo.entity.Due;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DueRepository extends JpaRepository<Due, Integer> {

    List<Due> findByUserIdOrderByYearDescMonthDesc(Integer userId);
    List<Due> findBySiteIdOrderByYearDescMonthDesc(Integer siteId);
}