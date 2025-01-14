package com.ytasharddrive.mappper;

import com.ytasharddrive.config.MapperConfig;
import com.ytasharddrive.dto.FileMetadataResponseDto;
import com.ytasharddrive.model.FileMetadata;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface FileMetadataMapper {

    FileMetadataResponseDto toDto(FileMetadata fileMetadata);
}
