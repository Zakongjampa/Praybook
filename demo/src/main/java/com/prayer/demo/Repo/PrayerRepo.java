package com.prayer.demo.Repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prayer.demo.dto.PrayerSummaryDTO;
import com.prayer.demo.utility.Prayer;

public interface PrayerRepo extends JpaRepository<Prayer, Long> {
    Page<Prayer> findAll(Pageable pageable);

    @Query("select new com.prayer.demo.dto.PrayerSummaryDTO(p.number, p.name) from Prayer p")
    List<PrayerSummaryDTO> findAllPrayerSummaries();

}
