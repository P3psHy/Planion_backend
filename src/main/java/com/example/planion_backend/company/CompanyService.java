package com.example.planion_backend.company;

import java.util.List;

import org.springframework.stereotype.Service;


@Service 
public class CompanyService {
    
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // CREATE
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    // READONE
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    // READALL
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // UPDATE
    public Company updateCompany(Long id, Company updatedCompany) {
        return companyRepository.findById(id)
                .map(company -> {
                    company.setName(updatedCompany.getName());
                    company.setCreationDate(updatedCompany.getCreationDate());
                    return companyRepository.save(company);
                })
                .orElse(null);
    }

    // DELETE
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    // Extra methods
    public List<Company> getCompaniesByName(String name) {
        return companyRepository.findAll().stream()
            .filter(company -> company.getName().equalsIgnoreCase(name))
            .toList();
    }

}
