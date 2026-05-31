package com.apartix.demo.repository;

import com.apartix.demo.entity.AnnouncementComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementCommentRepository extends JpaRepository<AnnouncementComment, Integer> {

    List<AnnouncementComment> findByAnnouncementIdOrderByCreatedAtAsc(Integer announcementId);
}