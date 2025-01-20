package com.ytasharddrive.service;

import java.io.IOException;
import java.io.InputStream;

public interface YouTubeService {

    String uploadVideo(InputStream videoStream, String title, String description)
            throws IOException;
}
