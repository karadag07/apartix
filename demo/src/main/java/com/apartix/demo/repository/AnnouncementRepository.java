package com.apartix.demo.repository;

import com.apartix.demo.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

    List<Announcement> findBySiteIdOrderByCreatedAtDesc(Integer siteId);

}