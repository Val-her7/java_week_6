package dev.val.COGIP_CLI.Command;

import dev.val.COGIP_CLI.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@Component
@CommandLine.Command(name = "register", description = "Register with username and password")
@RequiredArgsConstructor
public class RegisterCommand implements Callable<Integer> {

    private final AuthService authService;

    @CommandLine.Option(names = {"-u", "--username"}, required = true, description = "Username")
    private String username;

    @CommandLine.Option(names = {"-p", "--password"}, required = true, description = "Password")
    private String password;

    @Override
    public Integer call() {
        boolean success = authService.register(username, password);
        if (success) {
            System.out.println("Registration successful !");
            return 0;
        } else {
            System.out.println("Registration failed !");
            return 1;
        }
    }
}

