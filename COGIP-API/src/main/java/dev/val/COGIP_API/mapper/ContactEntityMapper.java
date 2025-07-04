package dev.val.COGIP_API.mapper;

import dev.val.COGIP_API.dto.ContactDTO;
import dev.val.COGIP_API.model.Company;
import dev.val.COGIP_API.model.Contact;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class ContactEntityMapper implements BiFunction<ContactDTO, Company, Contact> {

    @Override
    public Contact apply(ContactDTO contactDTO, Company company) {

        Contact contact = new Contact();
        contact.setFirstName(contactDTO.firstName());
        contact.setLastName(contactDTO.lastName());
        contact.setEmail(contactDTO.email());
        contact.setCompany(company);
        return contact;
    }
}
