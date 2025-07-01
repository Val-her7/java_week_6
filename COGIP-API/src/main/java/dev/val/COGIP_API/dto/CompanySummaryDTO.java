package dev.val.COGIP_API.dto;

import dev.val.COGIP_API.model.CompanyType;

public record CompanySummaryDTO(
        String name,
        String vatNumber,
        CompanyType companyType
) {
}
