package com.prayer.demo.Service;

import com.prayer.demo.dto.PrayerDTO;
import com.prayer.demo.mapper.PrayerMapper;
import com.prayer.demo.utility.Prayer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.prayer.demo.Repo.PrayerRepo;

@Service
public class PrayerService {
    PrayerRepo repo;

    public PrayerService(PrayerRepo repo) {
        this.repo = repo;
    }

    // Create
    public PrayerDTO createPrayer(PrayerDTO dto) {
        Prayer prayer = PrayerMapper.toEntity(dto);
        Prayer saved = repo.save(prayer);
        return PrayerMapper.toDTO(saved);
    }

    // READ : Get all prayers
    public List<PrayerDTO> getAllPrayers() {
        return repo.findAll()
                .stream()
                .map(PrayerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Page<Prayer> getPrayers(Pageable pageable) {
        return repo.findAll(pageable);
    }

    // Get Prayer by id
    public PrayerDTO getPrayerById(Long id) {
        return repo.findById(id)
                .map(PrayerMapper::toDTO)
                .orElse(null);
    }

    public PrayerDTO updatePrayer(Long id, PrayerDTO dto) {
        return repo.findById(id)
                .map(pr -> {
                    pr.setName(dto.getName());
                    pr.setContent(dto.getContent());
                    Prayer updated = repo.save(pr);
                    return PrayerMapper.toDTO(updated);
                })
                .orElse(null);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
