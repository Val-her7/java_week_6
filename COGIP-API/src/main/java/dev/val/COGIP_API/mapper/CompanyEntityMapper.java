package dev.val.COGIP_API.mapper;

import dev.val.COGIP_API.dto.CompanyDTO;
import dev.val.COGIP_API.model.Company;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CompanyEntityMapper implements Function<CompanyDTO, Company> {

    @Override
    public Company apply(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setName(companyDTO.name());
        company.setVatNumber(companyDTO.vatNumber());
        company.setCompanyType(companyDTO.companyType());
        return company;
    }
}
