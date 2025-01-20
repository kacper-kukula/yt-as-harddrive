package com.ytasharddrive.repository;

import com.ytasharddrive.model.FileMetadata;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileMetadataRepository extends MongoRepository<FileMetadata, String> {

    List<FileMetadata> findByUserId(String userId);
}
