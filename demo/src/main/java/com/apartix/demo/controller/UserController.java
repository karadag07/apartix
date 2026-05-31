package com.apartix.demo.controller;

import com.apartix.demo.dto.ChangePasswordRequest;
import com.apartix.demo.dto.RegisterRequest;
import com.apartix.demo.entity.Site;
import com.apartix.demo.entity.User;
import com.apartix.demo.repository.SiteRepository;
import com.apartix.demo.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;
    private final SiteRepository siteRepository;

    public UserController(UserRepository userRepository, SiteRepository siteRepository) {
        this.userRepository = userRepository;
        this.siteRepository = siteRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        if ("yonetici".equals(request.getRole())) {

            Site site = new Site();
            site.setName(request.getSiteName());
            site.setAddress(request.getSiteAddress());
            site.setPhone(request.getSitePhone());

            String joinCode = request.getSiteName()
                    .toUpperCase()
                    .replaceAll("\\s+", "-")
                    + "-"
                    + UUID.randomUUID().toString().substring(0, 5).toUpperCase();

            site.setJoinCode(joinCode);

            Site savedSite = siteRepository.save(site);

            user.setRole("admin");
            user.setSiteId(savedSite.getId());
            user.setApartmentNo(null);

        } else {

            Site site = siteRepository.findByJoinCode(request.getJoinCode())
                    .orElseThrow(() -> new RuntimeException("Geçersiz katılım kodu"));

            boolean apartmentTaken =
                    userRepository.existsBySiteIdAndApartmentNo(
                            site.getId(),
                            request.getApartmentNo()
                    );

            if (apartmentTaken) {
                throw new RuntimeException("Bu daire zaten kayıtlı");
            }

            user.setRole("resident");
            user.setSiteId(site.getId());
            user.setApartmentNo(request.getApartmentNo());
            user.setMoveInMonth(request.getMoveInMonth());
        }

        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/{id}/change-password")
public ResponseEntity<?> changePassword(
        @PathVariable Integer id,
        @RequestBody ChangePasswordRequest request
) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

    if (!user.getPassword().equals(request.getOldPassword())) {
        return ResponseEntity.badRequest().body("Mevcut şifre hatalı");
    }

    if (request.getNewPassword() == null || request.getNewPassword().length() < 6) {
        return ResponseEntity.badRequest().body("Yeni şifre en az 6 karakter olmalı");
    }

    user.setPassword(request.getNewPassword());
    userRepository.save(user);

    return ResponseEntity.ok("Şifre başarıyla değiştirildi");
}

@PutMapping("/forgot-password")
public ResponseEntity<?> forgotPassword(@RequestBody ChangePasswordRequest request) {

    User user = userRepository.findByEmail(request.getEmail())
            .orElse(null);

    if (user == null) {
        return ResponseEntity.badRequest().body("Bu e-posta ile kullanıcı bulunamadı");
    }

    if (request.getNewPassword() == null || request.getNewPassword().length() < 6) {
        return ResponseEntity.badRequest().body("Yeni şifre en az 6 karakter olmalı");
    }

    user.setPassword(request.getNewPassword());
    userRepository.save(user);

    return ResponseEntity.ok("Şifre başarıyla yenilendi");
}
}