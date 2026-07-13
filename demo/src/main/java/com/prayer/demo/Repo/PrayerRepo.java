package com.prayer.demo.Repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prayer.demo.utility.Prayer;

public interface PrayerRepo extends JpaRepository<Prayer, Long> {
    Page<Prayer> findAll(Pageable pageable);

}
