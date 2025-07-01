package dev.val.COGIP_API.dto;

import java.time.LocalDate;

public record InvoiceSummaryDTO(
        String number,
        LocalDate date
) {
}
