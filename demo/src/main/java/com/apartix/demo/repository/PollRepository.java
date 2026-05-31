package com.apartix.demo.repository;

import com.apartix.demo.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PollRepository extends JpaRepository<Poll, Integer> {

    List<Poll> findBySiteId(Integer siteId);

}