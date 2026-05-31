package com.apartix.demo.repository;

import com.apartix.demo.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    List<Vote> findByPollId(Integer pollId);

    List<Vote> findByUserId(Integer userId);

    boolean existsByPollIdAndUserId(Integer pollId, Integer userId);

}