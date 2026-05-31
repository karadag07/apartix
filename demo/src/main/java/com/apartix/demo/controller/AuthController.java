package com.apartix.demo.controller;

import com.apartix.demo.dto.LoginRequest;
import com.apartix.demo.dto.LoginResponse;
import com.apartix.demo.entity.User;
import com.apartix.demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

    Optional<User> userOptional =
            userRepository.findByEmail(request.getEmail());

    if (userOptional.isEmpty()) {
        return ResponseEntity.badRequest()
                .body("Geçersiz email veya şifre");
    }

    User user = userOptional.get();

    if (!user.getPassword().equals(request.getPassword())) {
        return ResponseEntity.badRequest()
                .body("Geçersiz email veya şifre");
    }

    String requestedRole = request.getRole();

    if ("yonetici".equals(requestedRole) && !"admin".equals(user.getRole())) {
        return ResponseEntity.badRequest()
                .body("Bu kullanıcı yönetici değildir");
    }

    if ("sakin".equals(requestedRole) && !"resident".equals(user.getRole())) {
        return ResponseEntity.badRequest()
                .body("Bu kullanıcı sakin değildir");
    }

    LoginResponse response = new LoginResponse();
response.setId(user.getId());
response.setSiteId(user.getSiteId());
response.setName(user.getName());
response.setEmail(user.getEmail());
response.setRole(user.getRole());
response.setApartmentNo(user.getApartmentNo());
response.setCreatedAt(user.getCreatedAt());
response.setMoveInMonth(user.getMoveInMonth());
return ResponseEntity.ok(response);
}
}