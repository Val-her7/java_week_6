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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContactById(@PathVariable int id, @RequestBody ContactDTO contactDTO) {
        ContactDTO updated = contactService.updateContactById(id, contactDTO);

        if(updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContactById(@PathVariable int id) {
        ContactDTO deleted = contactService.deleteContactById(id);

        if(deleted == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
        }
        return ResponseEntity.ok(deleted);
    }
}
