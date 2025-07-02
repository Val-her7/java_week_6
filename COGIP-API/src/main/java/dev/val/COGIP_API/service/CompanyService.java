package dev.val.COGIP_API.service;

import dev.val.COGIP_API.dto.CompanyDTO;
import dev.val.COGIP_API.mapper.CompanyDTOMapper;
import dev.val.COGIP_API.mapper.CompanyEntityMapper;
import dev.val.COGIP_API.model.Company;
import dev.val.COGIP_API.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyDTOMapper companyDTOMapper;
    private final CompanyEntityMapper companyEntityMapper;

    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll().stream().map(companyDTOMapper).toList();
    }

    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        Company company = companyEntityMapper.apply(companyDTO);
        Company saved = companyRepository.save(company);
        return companyDTOMapper.apply(saved);
    }
}
