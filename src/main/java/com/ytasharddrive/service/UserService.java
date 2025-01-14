package com.ytasharddrive.service;


import com.ytasharddrive.dto.UserRegistrationRequestDto;
import com.ytasharddrive.dto.UserResponseDto;
import com.ytasharddrive.exception.RegistrationException;

public interface UserService {

    UserResponseDto registerUser(UserRegistrationRequestDto requestDto)
            throws RegistrationException;
}
