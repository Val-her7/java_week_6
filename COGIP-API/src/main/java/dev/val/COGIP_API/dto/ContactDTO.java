package dev.val.COGIP_API.dto;

import java.util.List;

public record ContactDTO(
        String firstName,
        String lastName,
        String email,
        String companyName,
        List<InvoiceSummaryDTO> invoices
) {
}
