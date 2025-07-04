package dev.val.COGIP_API.controller;

import dev.val.COGIP_API.dto.ContactDTO;
import dev.val.COGIP_API.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable int id) {
        ContactDTO contactDTO = contactService.getContactById(id);

        if(contactDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
        }
        return ResponseEntity.ok(contactDTO);
    }

    @PostMapping
    public ResponseEntity<?> addContact(@RequestBody ContactDTO contactDTO) {
        ContactDTO newContact = contactService.createContact(contactDTO);

        if(newContact == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company provided for contact not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newContact);
    }
}
