package dev.val.COGIP_CLI;

import dev.val.COGIP_CLI.Command.CliRootCommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;

@SpringBootApplication
public class CogipCliApplication implements CommandLineRunner {

	private final CommandLine.IFactory factory;
	private final CommandLine commandLine;

	public CogipCliApplication(CliRootCommand rootCommand, CommandLine.IFactory factory) {
		this.factory = factory;
		this.commandLine = new CommandLine(rootCommand, factory);
	}

	public static void main(String[] args) {
		SpringApplication.run(CogipCliApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		int exitCode = commandLine.execute(args);
		System.exit(exitCode);
	}
}
