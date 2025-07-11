package dev.val.COGIP_API.mapper;

import dev.val.COGIP_API.dto.CompanySummaryDTO;
import dev.val.COGIP_API.dto.ContactSummaryDTO;
import dev.val.COGIP_API.dto.InvoiceDTO;
import dev.val.COGIP_API.model.Company;
import dev.val.COGIP_API.model.Contact;
import dev.val.COGIP_API.model.Invoice;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class InvoiceDTOMapper implements Function<Invoice, InvoiceDTO> {

    @Override
    public InvoiceDTO apply(Invoice invoice) {

        CompanySummaryDTO companySummaryDTO = new CompanySummaryDTO(invoice.getCompany().getName(),
                invoice.getCompany().getVatNumber(),
                invoice.getCompany().getCompanyType());

        ContactSummaryDTO contactSummaryDTO = new ContactSummaryDTO(invoice.getContact().getFirstName(),
                invoice.getContact().getLastName(),
                invoice.getContact().getEmail());

        return new InvoiceDTO(
                invoice.getNumber(),
                invoice.getDate(),
                companySummaryDTO,
                contactSummaryDTO
        );
    }

    public Invoice toEntity(InvoiceDTO invoiceDTO, Company company, Contact contact) {
        Invoice invoice = new Invoice();
        invoice.setNumber(invoiceDTO.number());
        invoice.setDate(invoiceDTO.date());
        invoice.setCompany(company);
        invoice.setContact(contact);
        return invoice;
    }
}
