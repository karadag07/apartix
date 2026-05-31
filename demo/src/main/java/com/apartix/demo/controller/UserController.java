package com.apartix.demo.controller;

import com.apartix.demo.dto.ChangePasswordRequest;
import com.apartix.demo.dto.RegisterRequest;
import com.apartix.demo.entity.Site;
import com.apartix.demo.entity.User;
import com.apartix.demo.repository.SiteRepository;
import com.apartix.demo.repository.UserRepository;
import com.apartix.demo.entity.Due;
import com.apartix.demo.repository.DueRepository;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
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
private final DueRepository dueRepository;

    public UserController(
        UserRepository userRepository,
        SiteRepository siteRepository,
        DueRepository dueRepository
) {
    this.userRepository = userRepository;
    this.siteRepository = siteRepository;
    this.dueRepository = dueRepository;
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

        User savedUser = userRepository.save(user);

if ("resident".equals(savedUser.getRole())) {

    int moveInMonth = savedUser.getMoveInMonth() != null
            ? savedUser.getMoveInMonth()
            : 1;

    for (int month = moveInMonth; month <= 12; month++) {

    Due due = new Due();

    due.setSiteId(savedUser.getSiteId());
    due.setUserId(savedUser.getId());
    due.setMonth(month);
    due.setYear(2026);

    BigDecimal amount = new BigDecimal("500.00");
    BigDecimal dailyRate = new BigDecimal("0.01");

    LocalDate now = LocalDate.now();
    LocalDate dueLocalDate = LocalDate.of(2026, month, 1);
    LocalDate lastPaymentDate = dueLocalDate.withDayOfMonth(dueLocalDate.lengthOfMonth());

    BigDecimal lateFee = BigDecimal.ZERO;
    BigDecimal remainingAmount = BigDecimal.ZERO;

    if (month <= now.getMonthValue()) {

        long lateDays = 0;

        if (now.isAfter(lastPaymentDate)) {
            lateDays = java.time.temporal.ChronoUnit.DAYS.between(lastPaymentDate, now);
        }

        lateFee = amount
                .multiply(dailyRate)
                .multiply(BigDecimal.valueOf(lateDays));

        remainingAmount = amount.add(lateFee);
    }

    due.setAmount(amount);
    due.setLateFee(lateFee);
    due.setRemainingAmount(remainingAmount);
    due.setStatus("unpaid");
    due.setDueDate(Date.valueOf(dueLocalDate));

    dueRepository.save(due);
}
}

return savedUser;
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