package com.ytasharddrive.service.impl;

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;
import com.ytasharddrive.service.YouTubeService;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class YouTubeServiceImpl implements YouTubeService {

    private final YouTube youtube;

    public String uploadVideo(InputStream videoStream, String title, String description)
            throws IOException {
        Video video = new Video();

        VideoStatus status = new VideoStatus();
        status.setPrivacyStatus("private");
        video.setStatus(status);

        VideoSnippet snippet = new VideoSnippet();
        snippet.setTitle(title);
        snippet.setDescription(description);
        snippet.setTags(Collections.singletonList("QR Code Storage"));
        video.setSnippet(snippet);

        YouTube.Videos.Insert request = youtube.videos().insert(
                "snippet,status",
                video,
                new InputStreamContent("video/*", videoStream)
        );

        Video response = request.execute();

        return response.getId();
    }
}
