package dev.val.COGIP_CLI.Service;

import dev.val.COGIP_CLI.Security.TokenStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ApiService apiService;
    private final TokenStorage tokenStorage;

    public boolean login(String username, String password) {
        try {
            String token = apiService.login(username, password);
            tokenStorage.saveToken(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean register(String username, String password) {
        try {
            apiService.register(username, password);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getToken() throws IOException {
        return tokenStorage.loadToken();
    }
}
