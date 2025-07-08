package dev.val.COGIP_API.service;

import dev.val.COGIP_API.dto.ContactDTO;
import dev.val.COGIP_API.mapper.ContactDTOMapper;
import dev.val.COGIP_API.mapper.ContactEntityMapper;
import dev.val.COGIP_API.model.Company;
import dev.val.COGIP_API.model.Contact;
import dev.val.COGIP_API.repository.CompanyRepository;
import dev.val.COGIP_API.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactDTOMapper contactDTOMapper;
    private final CompanyRepository companyRepository;
    private final ContactEntityMapper contactEntityMapper;

    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll().stream().map(contactDTOMapper).toList();
    }

    public ContactDTO getContactById(int id) {
        Contact contact = contactRepository.findById(id).orElse(null);

        if(contact == null) return null;

        return contactDTOMapper.apply(contact);
    }

    public ContactDTO createContact(ContactDTO contactDTO) {
        Company company = companyRepository.findByName(contactDTO.companyName()).orElse(null);

        if(company == null) return null;
        Contact contact = contactEntityMapper.apply(contactDTO, company);
        Contact saved = contactRepository.save(contact);
        return contactDTOMapper.apply(saved);
    }

    public ContactDTO updateContactById(int id, ContactDTO contactDTO) {
        Contact contact = contactRepository.findById(id).orElse(null);

        if(contact == null) return null;

        contact.setFirstName(contactDTO.firstName());
        contact.setLastName(contactDTO.lastName());
        contact.setEmail(contactDTO.email());

        Contact updated = contactRepository.save(contact);
        return contactDTOMapper.apply(updated);
    }

    public ContactDTO deleteContactById(int id) {
        Contact contact = contactRepository.findById(id).orElse(null);

        if(contact == null) return null;

        ContactDTO deleted = contactDTOMapper.apply(contact);
        contactRepository.deleteById(id);
        return deleted;
    }
}
