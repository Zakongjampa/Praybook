package com.prayer.demo.mapper;

import com.prayer.demo.dto.PrayerDTO;
import com.prayer.demo.utility.Prayer;

public class PrayerMapper {
    public static Prayer toEntity(PrayerDTO dto) {
        if (dto == null) {
            return null;
        }
        Prayer prayer = new Prayer();
        if (dto.getNumber() != null) {
            prayer.setNumber(dto.getNumber());
        }
        prayer.setName(dto.getName());
        prayer.setContent(dto.getContent());
        return prayer;
    }

    public static PrayerDTO toDTO(Prayer prayer) {
        if (prayer == null) {
            return null;
        }
        return new PrayerDTO(
            prayer.getNumber(),
            prayer.getName(),
            prayer.getContent()
        );
    }
}
