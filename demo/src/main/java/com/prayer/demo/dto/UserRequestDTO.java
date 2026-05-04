package com.prayer.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
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
public class UserRequestDTO {
    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Email should be valid")
    private String email; // Made optional since it's not in database schema

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Password is required")
    private String hashedPassword;

}
