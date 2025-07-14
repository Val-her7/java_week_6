package dev.val.COGIP_CLI.Command;

import dev.val.COGIP_CLI.Service.ApiService;
import dev.val.COGIP_CLI.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.io.IOException;
import java.util.concurrent.Callable;

@Component
@CommandLine.Command(name = "company", description = "Manage companies",
        subcommands = {
                CompanyCommand.List.class,
                CompanyCommand.Add.class
        })
@RequiredArgsConstructor
public class CompanyCommand implements Runnable {

    @Override
    public void run() {
        System.out.println("Use a sub-command: list, add, update, delete");
    }

    @CommandLine.Command(name = "list", description = "List of companies")
    @Component
    @RequiredArgsConstructor
    static class List implements Callable<Integer> {

        private final AuthService authService;
        private final ApiService apiService;

        @Override
        public Integer call() throws Exception {
            String token = authService.getToken();
            if (token == null) {
                System.err.println("Please login first!");
                return 1;
            }
            String companiesJson = apiService.getCompanies(token);
            System.out.println(companiesJson);
            return 0;
        }
    }

    @CommandLine.Command(name = "add", description = "Add a new company")
    @Component
    @RequiredArgsConstructor
    static class Add implements Callable<Integer> {

        private final AuthService authService;
        private final ApiService apiService;

        @CommandLine.Option(names = {"-n", "--name"}, description = "Company name", required = true)
        private String name;

        @CommandLine.Option(names = {"-v", "--vat"}, description = "VAT number", required = true)
        private String vatNumber;

        @CommandLine.Option(names = {"-t", "--type"}, description = "Company type", required = true)
        private String companyType;

        @Override
        public Integer call() throws Exception {
            String token = authService.getToken();
            if(token == null) {
                System.err.println("Please login first!");
                return 1;
            }
            String newCompanyJson = apiService.createCompany(name, vatNumber, companyType, token);
            System.out.println(newCompanyJson);
            return 0;
        }
    }

}

