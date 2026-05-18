package com.prayer.demo.Repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.prayer.demo.utility.LikedPrayer;
import com.prayer.demo.utility.Prayer;
import com.prayer.demo.utility.User;

@Repository
public interface LikedPrayerRepo extends JpaRepository<LikedPrayer, Long> {
    boolean existsByUserAndPrayer(User user, Prayer prayer);

    void deleteByUserAndPrayer(User user, Prayer prayer);

    long countByPrayer(Prayer prayer);

    List<LikedPrayer> findByUser(User user);

    @Query("""
            SELECT l.prayer
            FROM LikedPrayer l
            GROUP BY l.prayer
            ORDER BY COUNT(l.prayer) DESC
            """)
    List<Prayer> findMostLikedPrayers(Pageable pageable);
}
