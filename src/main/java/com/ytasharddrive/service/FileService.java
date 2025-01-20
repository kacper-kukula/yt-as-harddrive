package com.ytasharddrive.service;

import com.ytasharddrive.dto.FileMetadataResponseDto;
import com.ytasharddrive.dto.FileUploadResponseDto;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    FileUploadResponseDto saveFile(MultipartFile file);

    List<FileMetadataResponseDto> listFiles();

    Resource getFileAsResource(String fileId);

}
