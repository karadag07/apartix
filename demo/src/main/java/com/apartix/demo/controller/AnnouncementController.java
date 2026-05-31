package com.apartix.demo.controller;

import com.apartix.demo.dto.CreateAnnouncementRequest;
import com.apartix.demo.entity.Announcement;
import com.apartix.demo.repository.AnnouncementRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
@CrossOrigin(origins = "*")
public class AnnouncementController {

    private final AnnouncementRepository announcementRepository;

    public AnnouncementController(
            AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @GetMapping
    public List<Announcement> getAnnouncements(
            @RequestParam Integer siteId) {

        return announcementRepository
                .findBySiteIdOrderByCreatedAtDesc(siteId);
    }

    @PostMapping
public Announcement createAnnouncement(
        @RequestBody CreateAnnouncementRequest request) {

    Announcement announcement = new Announcement();

    announcement.setSiteId(request.getSiteId());
    announcement.setAdminId(request.getAdminId());
    announcement.setTitle(request.getTitle());
    announcement.setContent(request.getContent());

    announcement.setCreatedAt(
            new java.sql.Timestamp(System.currentTimeMillis()));

    return announcementRepository.save(announcement);
}

@DeleteMapping("/{id}")
public void deleteAnnouncement(@PathVariable Integer id) {
    announcementRepository.deleteById(id);
}
}