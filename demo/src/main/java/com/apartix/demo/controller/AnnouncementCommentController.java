package com.apartix.demo.controller;

import com.apartix.demo.dto.CreateCommentRequest;
import com.apartix.demo.entity.AnnouncementComment;
import com.apartix.demo.repository.AnnouncementCommentRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/announcement-comments")
@CrossOrigin(origins = "*")
public class AnnouncementCommentController {

    private final AnnouncementCommentRepository commentRepository;

    public AnnouncementCommentController(AnnouncementCommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/{announcementId}")
    public List<AnnouncementComment> getComments(@PathVariable Integer announcementId) {
        return commentRepository.findByAnnouncementIdOrderByCreatedAtAsc(announcementId);
    }

    @PostMapping
    public AnnouncementComment createComment(@RequestBody CreateCommentRequest request) {
        AnnouncementComment comment = new AnnouncementComment();

        comment.setAnnouncementId(request.getAnnouncementId());
        comment.setUserId(request.getUserId());
        comment.setComment(request.getComment());
        comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        return commentRepository.save(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Integer id) {
        commentRepository.deleteById(id);
    }
}