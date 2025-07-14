package dev.val.COGIP_CLI.Command;

import dev.val.COGIP_CLI.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@Component
@CommandLine.Command(name = "login", description = "Login with username and password")
@RequiredArgsConstructor
public class LoginCommand implements Callable<Integer> {

    private final AuthService authService;

    @CommandLine.Option(names = {"-u", "--username"}, required = true)
    private String username;

    @CommandLine.Option(names = {"-p", "--password"}, required = true)
    private String password;

    @Override
    public Integer call() throws Exception {
        boolean success = authService.login(username, password);
        if(success) {
            System.out.println("Login success !");
            return 0;
        } else {
            System.out.println("Login fail !");
            return 1;
        }
    }
}
