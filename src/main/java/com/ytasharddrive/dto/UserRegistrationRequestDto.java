package com.ytasharddrive.dto;

import com.example.medrecordsapi.security.validator.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@FieldMatch(
        first = "password",
        second = "repeatPassword",
        message = "The passwords must be identical"
)
public record UserRegistrationRequestDto(
        @NotBlank
        @Email
        String email,

        @NotBlank
        @Length(min = 8, max = 20)
        String password,

        @NotBlank
        @Length(min = 8, max = 20)
        String repeatPassword,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName
) {
}
