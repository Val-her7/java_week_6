package dev.val.COGIP_API.controller;

import dev.val.COGIP_API.dto.InvoiceDTO;
import dev.val.COGIP_API.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable int id) {
        InvoiceDTO invoiceDTO = invoiceService.getInvoiceById(id);

        if(invoiceDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice not found");
        }
        return ResponseEntity.ok(invoiceDTO);
    }

    @PostMapping
    public ResponseEntity<?> addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        InvoiceDTO newInvoice = invoiceService.createInvoice(invoiceDTO);

        if(newInvoice == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company or contact provided not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newInvoice);
    }
}
