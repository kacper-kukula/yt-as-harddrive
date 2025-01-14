package com.ytasharddrive.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "fileMetadata")
public class FileMetadata {
    @Id
    private String id;
    private String userId;
    private String fileName;
    private long fileSize;
    private long dateUploaded;
    private String youtubeLink;
}
