package com.ytasharddrive.config;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YouTubeConfig {

    private static final String CLIENT_SECRET_PATH = "/client_secret.json";
    private static final String APPLICATION_NAME = "YouTube as Harddrive";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final Collection<String> SCOPES = Arrays.asList(
            YouTubeScopes.YOUTUBE_READONLY,
            YouTubeScopes.YOUTUBE_UPLOAD);

    @Bean
    public YouTube youtube() throws IOException, GeneralSecurityException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Credential credential = authorize(httpTransport);

        return new YouTube.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static Credential authorize(final NetHttpTransport httpTransport) throws IOException {
        InputStream in = YouTubeConfig.class.getResourceAsStream(CLIENT_SECRET_PATH);

        if (in == null) {
            throw new IOException("Could not find client_secret.json. "
                    + "Please ensure the file is in the resources folder.");
        }

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport,
                JSON_FACTORY,
                clientSecrets,
                SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder()
                .setPort(53709)
                .build();

        return new AuthorizationCodeInstalledApp(flow,
                receiver).authorize("user");
    }
}
