package dev.val.COGIP_CLI.Security;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class TokenStorage {

    private static final Path TOKEN_PATH = Path.of(System.getProperty("user.home"), ".cogip_token");

    public void saveToken(String token) throws IOException {
        Files.writeString(TOKEN_PATH, token);
    }

    public String loadToken() throws IOException {
        if (Files.exists(TOKEN_PATH)) {
            return Files.readString(TOKEN_PATH);
        }
        return null;
    }
}
