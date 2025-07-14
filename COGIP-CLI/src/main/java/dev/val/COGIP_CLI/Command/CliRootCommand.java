package dev.val.COGIP_CLI.Command;

import org.springframework.stereotype.Component;
import picocli.CommandLine;

@Component
@CommandLine.Command(name = "cogip-cli",
        mixinStandardHelpOptions = true,
        subcommands = {
                LoginCommand.class,
                RegisterCommand.class,
                CompanyCommand.class
        })
public class CliRootCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Use a command. --help for more infos.");
    }
}

