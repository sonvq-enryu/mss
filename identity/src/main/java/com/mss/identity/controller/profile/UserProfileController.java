package com.mss.identity.controller.profile;

import com.mss.identity.service.security.UserInformationDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-profile")
@RequiredArgsConstructor
public class UserProfileController {

    @GetMapping
    public ResponseEntity<?> getUserProfile() {
        var user = (UserInformationDomain) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<?> updateUserProfile() {
        var userEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok("Hello " + userEmail);
    }
}
