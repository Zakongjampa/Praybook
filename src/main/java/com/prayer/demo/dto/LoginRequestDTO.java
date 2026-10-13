package com.prayer.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequestDTO {
    @jakarta.validation.constraints.NotBlank
    private String username;

    @jakarta.validation.constraints.NotBlank
    private String password;
}
