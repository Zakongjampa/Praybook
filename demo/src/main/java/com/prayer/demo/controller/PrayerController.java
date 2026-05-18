package com.prayer.demo.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prayer.demo.Service.PrayerService;
import com.prayer.demo.dto.PrayerDTO;
import com.prayer.demo.utility.Prayer;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5175")
@RestController
public class PrayerController {
    PrayerService ps;

    public PrayerController(PrayerService ps) {
        this.ps = ps;
    }

    // Create
    @PostMapping("/prayer")
    public ResponseEntity<PrayerDTO> createPrayer(@Valid @RequestBody PrayerDTO dto) {
        PrayerDTO created = ps.createPrayer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Read: Get by ID
    @GetMapping("/prayer/{id}")
    public ResponseEntity<PrayerDTO> getPrayerById(@PathVariable Long id) {
        PrayerDTO prayer = ps.getPrayerById(id);
        if (prayer != null) {
            return ResponseEntity.ok(prayer);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Read: Get all
    @GetMapping("/prayer")
    public ResponseEntity<List<PrayerDTO>> getPrayers() {
        List<PrayerDTO> prayers = ps.getAllPrayers();
        return ResponseEntity.ok(prayers);
    }

    @GetMapping("/prayerList")
    public Page<Prayer> getTopPrayers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ps.getPrayers(pageable);

    }

    // Update
    @PutMapping("/prayer/{id}")
    public ResponseEntity<PrayerDTO> updatePrayer(@PathVariable Long id, @Valid @RequestBody PrayerDTO dto) {
        PrayerDTO updated = ps.updatePrayer(id, dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Delete
    @DeleteMapping("/prayer/{id}")
    public ResponseEntity<Void> deletePrayer(@PathVariable Long id) {
        ps.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
