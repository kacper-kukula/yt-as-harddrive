package com.ytasharddrive.mappper;

import com.ytasharddrive.config.MapperConfig;
import com.ytasharddrive.dto.UserRegistrationRequestDto;
import com.ytasharddrive.dto.UserResponseDto;
import com.ytasharddrive.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto requestDto);
}
