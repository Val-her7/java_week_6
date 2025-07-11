package dev.val.COGIP_API.service;

import dev.val.COGIP_API.dto.InvoiceDTO;
import dev.val.COGIP_API.mapper.InvoiceDTOMapper;
import dev.val.COGIP_API.model.Invoice;
import dev.val.COGIP_API.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceDTOMapper invoiceDTOMapper;

    public List<InvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll().stream().map(invoiceDTOMapper).toList();
    }

    public InvoiceDTO getInvoiceById(int id){
        Invoice invoice = invoiceRepository.findById(id).orElse(null);

        if(invoice == null) return null;

        return invoiceDTOMapper.apply(invoice);
    }
}
