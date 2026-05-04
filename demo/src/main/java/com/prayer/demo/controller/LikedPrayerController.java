package com.prayer.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prayer.demo.Service.LikecPrayerService;

@CrossOrigin(origins = "http://localhost:5175")
@RequestMapping("/liked")
@RestController
public class LikedPrayerController {
    private final LikecPrayerService service;

    public LikedPrayerController(LikecPrayerService service) {
        this.service = service;
    }

    @PostMapping("/{userId}/{prayerId}")
    public ResponseEntity<Void> likePrayer(@PathVariable Long userId, @PathVariable Long prayerId) {
        service.likePrayer(userId, prayerId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{userId}/{prayerId}")
    public ResponseEntity<Void> unlikePrayer(@PathVariable Long userId, @PathVariable Long prayerId) {
        service.unlikePrayer(userId, prayerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Long>> getLikedByUser(@PathVariable Long userId) {
        List<Long> likedIds = service.likedByUser(userId);
        return ResponseEntity.ok(likedIds);
    }

}
