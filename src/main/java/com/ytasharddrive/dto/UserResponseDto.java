package com.ytasharddrive.dto;

import com.ytasharddrive.model.Role;

public record UserResponseDto(
        String id,
        String email,
        String firstName,
        String lastName,
        Role role
) {
}
