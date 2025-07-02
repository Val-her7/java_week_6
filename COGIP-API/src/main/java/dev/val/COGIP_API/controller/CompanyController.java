package dev.val.COGIP_API.controller;

import dev.val.COGIP_API.dto.CompanyDTO;
import dev.val.COGIP_API.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> addCompany(@RequestBody CompanyDTO companyDTO) {
        CompanyDTO newCompany = companyService.createCompany(companyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCompany);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable int id) {
        CompanyDTO companyDTO = companyService.getCompanyById(id);

        if(companyDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        }
        return ResponseEntity.ok(companyDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompanyById(@PathVariable int id, @RequestBody CompanyDTO companyDTO) {
        CompanyDTO updated = companyService.updateCompanyById(id, companyDTO);

        if(updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompanyById(@PathVariable int id) {
        CompanyDTO deleted = companyService.deleteCompanyById(id);

        if(deleted == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        }
        return ResponseEntity.ok(deleted);
    }
}
