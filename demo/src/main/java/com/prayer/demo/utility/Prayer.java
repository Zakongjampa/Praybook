package com.prayer.demo.utility;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Prayer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long number;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "prayer")
    private List<LikedPrayer> likedByUsers = new ArrayList<LikedPrayer>();

}
