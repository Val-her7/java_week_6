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

    public CompanyDTO getCompanyById(int id) {
        Company company = companyRepository.findById(id).orElse(null);

        if(company == null) return null;

        return companyDTOMapper.apply(company);
    }

    public CompanyDTO updateCompanyById(int id, CompanyDTO companyDTO) {
        Company company = companyRepository.findById(id).orElse(null);

        if(company == null) return null;

        company.setName(companyDTO.name());
        company.setVatNumber(companyDTO.vatNumber());
        company.setCompanyType(companyDTO.companyType());

        Company updated = companyRepository.save(company);
        return companyDTOMapper.apply(updated);
    }

    public CompanyDTO deleteCompanyById(int id) {
        Company company = companyRepository.findById(id).orElse(null);

        if(company == null) return null;

        CompanyDTO deletedCompany = companyDTOMapper.apply(company);
        companyRepository.deleteById(id);
        return deletedCompany;
    }
}
