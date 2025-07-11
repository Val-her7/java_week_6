package dev.val.COGIP_API.service;

import dev.val.COGIP_API.dto.InvoiceDTO;
import dev.val.COGIP_API.mapper.InvoiceDTOMapper;
import dev.val.COGIP_API.model.Company;
import dev.val.COGIP_API.model.Contact;
import dev.val.COGIP_API.model.Invoice;
import dev.val.COGIP_API.repository.CompanyRepository;
import dev.val.COGIP_API.repository.ContactRepository;
import dev.val.COGIP_API.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceDTOMapper invoiceDTOMapper;
    private final CompanyRepository companyRepository;
    private final ContactRepository contactRepository;

    public List<InvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll().stream().map(invoiceDTOMapper).toList();
    }

    public InvoiceDTO getInvoiceById(int id){
        Invoice invoice = invoiceRepository.findById(id).orElse(null);

        if(invoice == null) return null;

        return invoiceDTOMapper.apply(invoice);
    }

    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        Company company = companyRepository.findByName(invoiceDTO.companySummaryDTO().name()).orElse(null);
        Contact contact = contactRepository.findByEmail(invoiceDTO.contactSummaryDTO().email()).orElse(null);

        if(company == null || contact == null) return null;

        Invoice invoice = invoiceDTOMapper.toEntity(invoiceDTO, company, contact);
        invoiceRepository.save(invoice);

        return invoiceDTOMapper.apply(invoice);
    }
}
