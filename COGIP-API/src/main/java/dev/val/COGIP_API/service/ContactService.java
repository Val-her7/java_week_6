package dev.val.COGIP_API.service;

import dev.val.COGIP_API.dto.ContactDTO;
import dev.val.COGIP_API.mapper.ContactDTOMapper;
import dev.val.COGIP_API.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactDTOMapper contactDTOMapper;

    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll().stream().map(contactDTOMapper).toList();
    }
}
