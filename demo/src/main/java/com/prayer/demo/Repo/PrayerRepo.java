package com.prayer.demo.Repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prayer.demo.utility.Prayer;

@Repository
public interface PrayerRepo extends JpaRepository<Prayer, Long> {
    Page<Prayer> findAll(Pageable pageable);

}
