package com.ytasharddrive.dto;

public record FileMetadataResponseDto(
        String id,
        String fileName,
        long fileSize,
        long dateUploaded
) {
}
