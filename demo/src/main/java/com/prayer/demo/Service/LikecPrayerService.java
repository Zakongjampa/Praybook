package com.prayer.demo.Service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prayer.demo.Repo.LikedPrayerRepo;
import com.prayer.demo.Repo.PrayerRepo;
import com.prayer.demo.Repo.UserRepo;
import com.prayer.demo.utility.LikedPrayer;
import com.prayer.demo.utility.Prayer;
import com.prayer.demo.utility.User;

@Service
public class LikecPrayerService {
    private final LikedPrayerRepo likedRepo;
    private final PrayerRepo prayerRepo;
    private final UserRepo userRepo;

    public LikecPrayerService(
            LikedPrayerRepo likeRepo,
            PrayerRepo prayerRepo,
            UserRepo userRepo) {
        this.likedRepo = likeRepo;
        this.prayerRepo = prayerRepo;
        this.userRepo = userRepo;
    }

    // liked
    @Transactional
    public void likePrayer(Long userId, Long prayerId) {
        User user = getUserOrThrow(userId);
        Prayer prayer = getPrayerOrThrow(prayerId);

        if (likedRepo.existsByUserAndPrayer(user, prayer)) {
            return;
        }

        LikedPrayer likePrayer = new LikedPrayer();
        likePrayer.setPrayer(prayer);
        likePrayer.setUser(user);

        likedRepo.save(likePrayer);
    }

    // unliked
    @Transactional
    public void unlikePrayer(Long userId, Long prayerId) {
        User user = getUserOrThrow(userId);
        Prayer prayer = getPrayerOrThrow(prayerId);

        likedRepo.deleteByUserAndPrayer(user, prayer);
    }

    // getAllLiked prayer IDs
    public List<Long> likedByUser(Long id) {
        User user = getUserOrThrow(id);
        List<LikedPrayer> all = likedRepo.findByUser(user);
        // map to prayer ids only
        return all.stream().map(lp -> lp.getPrayer().getNumber())
                .toList();
    }

    private User getUserOrThrow(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    private Prayer getPrayerOrThrow(Long prayerId) {
        return prayerRepo.findById(prayerId)
                .orElseThrow(() -> new RuntimeException("Prayer Not Found"));
    }

    public List<Prayer> getTop10MostLikedPrayers() {
        return likedRepo.findMostLikedPrayers(PageRequest.of(0, 10));
    }

}
