package com.ytasharddrive.service.impl;

import com.ytasharddrive.dto.FileMetadataResponseDto;
import com.ytasharddrive.dto.FileUploadResponseDto;
import com.ytasharddrive.mappper.FileMetadataMapper;
import com.ytasharddrive.repository.FileMetadataRepository;
import com.ytasharddrive.service.FileService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileMetadataRepository fileMetadataRepository;
    private final FileMetadataMapper fileMetadataMapper;

    @Override
    public FileUploadResponseDto saveFile(MultipartFile file) {
        return null;
    }

    @Override
    public List<FileMetadataResponseDto> listFiles() {
        return fileMetadataRepository.findAll().stream()
                .map(fileMetadataMapper::toDto)
                .toList();
    }

    @Override
    public Resource getFileAsResource(String fileId) {
        return null;
    }
}
