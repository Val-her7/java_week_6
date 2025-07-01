package dev.val.COGIP_API.dto;

import dev.val.COGIP_API.model.CompanyType;

import java.util.List;

public record CompanyDTO(
        String name,
        String vatNumber,
        CompanyType companyType,
        List<ContactSummaryDTO> contacts,
        List<InvoiceSummaryDTO> invoices
) {
}
