package com.prayer.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrayerDTO {
    private Long number;

    @NotBlank(message = "Prayer name is required")
    private String name;

    @NotBlank(message = "Prayer content is required")
    private String content;
}
