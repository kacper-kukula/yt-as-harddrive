package com.ytasharddrive.controller;

import com.ytasharddrive.dto.FileMetadataResponseDto;
import com.ytasharddrive.dto.FileUploadResponseDto;
import com.ytasharddrive.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "File Management", description = "Endpoints for uploading, downloading, and managing files")
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileService fileService;

    @Operation(summary = "Upload a file",
            description = "Uploads a file and saves its metadata.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "File uploaded successfully"),
            @ApiResponse(responseCode = "400",
                    description = "Invalid file or file size exceeded")})
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public FileUploadResponseDto uploadFile(@RequestParam("file") @NotNull MultipartFile file) {
        log.info("Received file upload request: {}", file.getOriginalFilename());
        FileUploadResponseDto response = fileService.saveFile(file);
        log.info("File uploaded successfully with ID: {}", response.fileId());

        return response;
    }

    @Operation(summary = "List all uploaded files",
            description = "Retrieves metadata of all uploaded files.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Files retrieved successfully")})
    @GetMapping
    public List<FileMetadataResponseDto> listFiles() {
        log.info("Received request to list all uploaded files");
        List<FileMetadataResponseDto> files = fileService.listFiles();
        log.info("Returning {} files", files.size());

        return files;
    }

    @Operation(summary = "Download a file",
            description = "Downloads a file by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "File downloaded successfully"),
            @ApiResponse(responseCode = "404",
                    description = "File not found")})
    @GetMapping("/{fileId}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileId") String fileId) {
        log.info("Received request to download file with ID: {}", fileId);
        Resource fileResource = fileService.getFileAsResource(fileId);
        String contentDisposition = "attachment; filename=\"" + fileResource.getFilename() + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileResource);
    }
}
