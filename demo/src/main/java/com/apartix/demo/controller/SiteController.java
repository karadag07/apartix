package com.apartix.demo.controller;

import com.apartix.demo.entity.Site;
import com.apartix.demo.repository.SiteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sites")
@CrossOrigin(origins = "*")
public class SiteController {

    private final SiteRepository siteRepository;

    public SiteController(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @GetMapping
    public List<Site> getSites() {
        return siteRepository.findAll();
    }
    @GetMapping("/{id}")
public Site getSite(@PathVariable Integer id) {
    return siteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Site bulunamadı"));
}
}