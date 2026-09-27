package com.example.planion_backend.role;

import java.util.List;

import com.example.planion_backend.company.*;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final CompanyRepository companyRepository;

    public RoleService(RoleRepository roleRepository, CompanyRepository companyRepository) {
        this.roleRepository = roleRepository;
        this.companyRepository = companyRepository;
    }

    // CREATE
    public Role createRole(Role role, Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("Company not found with id: " + companyId));
        role.setCompany(company);
        return roleRepository.save(role);
    }

    // READONE
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    // READALL
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // UPDATE
    public Role updateRole(Long id, Role updatedRole) {
        return roleRepository.findById(id)
                .map(role -> {
                    role.setName(updatedRole.getName());
                    return roleRepository.save(role);
                })
                .orElse(null);
    }

    // DELETE
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
    
    // Extra methods
    public List<Role> getRolesByCompanyId(Long companyId) {
        return roleRepository.findAll().stream()
                .filter(role -> role.getCompany() != null && role.getCompany().getId().equals(companyId))
                .toList();
    }
}
