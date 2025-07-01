package dev.val.COGIP_API.dto;

import java.time.LocalDate;

public record InvoiceDTO(
        String number,
        LocalDate date,
        CompanySummaryDTO companySummaryDTO,
        ContactSummaryDTO contactSummaryDTO
) {
}
