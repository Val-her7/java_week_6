package dev.val.COGIP_API.mapper;

import dev.val.COGIP_API.dto.CompanyDTO;
import dev.val.COGIP_API.dto.ContactSummaryDTO;
import dev.val.COGIP_API.dto.InvoiceSummaryDTO;
import dev.val.COGIP_API.model.Company;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class CompanyDTOMapper implements Function<Company, CompanyDTO> {

    @Override
    public CompanyDTO apply(Company company) {

        List<ContactSummaryDTO> contacts = company.getContacts().stream()
                .map(c -> new ContactSummaryDTO(c.getFirstName(), c.getLastName(), c.getEmail()))
                .toList();

        List<InvoiceSummaryDTO> invoices = company.getInvoices().stream()
                .map(i ->new InvoiceSummaryDTO(i.getNumber(), i.getDate()))
                .toList();

        return new CompanyDTO(
              company.getName(),
              company.getVatNumber(),
              company.getCompanyType(),
              contacts,
                invoices
        );
    }
}
