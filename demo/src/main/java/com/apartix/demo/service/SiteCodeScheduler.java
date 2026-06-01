package com.apartix.demo.service;

import com.apartix.demo.entity.Site;
import com.apartix.demo.repository.SiteRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SiteCodeScheduler {

    private final SiteRepository siteRepository;

    public SiteCodeScheduler(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Scheduled(fixedRate = 300000)
    public void updateJoinCodes() {
        for (Site site : siteRepository.findAll()) {
            String newCode = site.getName()
                    .toUpperCase()
                    .replaceAll("\\s+", "-")
                    + "-"
                    + UUID.randomUUID().toString().substring(0, 5).toUpperCase();

            site.setJoinCode(newCode);
            siteRepository.save(site);
        }
    }
}