package dev.val.COGIP_API.service;

import dev.val.COGIP_API.dto.CompanyDTO;
import dev.val.COGIP_API.mapper.CompanyDTOMapper;
import dev.val.COGIP_API.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyDTOMapper companyDTOMapper;

    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll().stream().map(companyDTOMapper).toList();
    }
}
