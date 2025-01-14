package com.ytasharddrive.service.impl;

import com.ytasharddrive.dto.FileMetadataResponseDto;
import com.ytasharddrive.dto.FileUploadResponseDto;
import com.ytasharddrive.service.FileService;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public FileUploadResponseDto saveFile(MultipartFile file) {
        return null;
    }

    @Override
    public List<FileMetadataResponseDto> listFiles() {
        return null;
    }

    @Override
    public Resource getFileAsResource(String fileId) {
        return null;
    }
}
