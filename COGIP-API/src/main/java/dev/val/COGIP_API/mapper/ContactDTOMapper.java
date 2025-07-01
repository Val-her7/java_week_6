package dev.val.COGIP_API.mapper;

import dev.val.COGIP_API.dto.ContactDTO;
import dev.val.COGIP_API.dto.InvoiceSummaryDTO;
import dev.val.COGIP_API.model.Contact;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class ContactDTOMapper implements Function<Contact, ContactDTO> {

    @Override
    public ContactDTO apply(Contact contact) {

        List<InvoiceSummaryDTO> invoices = contact.getInvoices().stream()
                .map(i -> new InvoiceSummaryDTO(i.getNumber(), i.getDate()))
                .toList();

        return new ContactDTO(
                contact.getFirstName(),
                contact.getLastName(),
                contact.getEmail(),
                contact.getCompany().getName(),
                invoices
        );
    }
}
