package com.apartix.demo.repository;

import com.apartix.demo.entity.PollOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PollOptionRepository extends JpaRepository<PollOption, Integer> {

    List<PollOption> findByPollId(Integer pollId);

}