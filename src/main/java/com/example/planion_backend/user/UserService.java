package com.example.planion_backend.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.planion_backend.role.*;
import com.example.planion_backend.company.*;



@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CompanyRepository companyRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.companyRepository = companyRepository;
    }

    // CRUD OPERATIONS
    // CREATE
    public User createUser(User user, Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("Company not found with id: " + companyId));
        user.setCompany(company);
        return userRepository.save(user);
    }

    // READONE
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // READALL
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    // UPDATE
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(updatedUser.getFirstName());
                    user.setLastName(updatedUser.getLastName());
                    user.setTelephone(updatedUser.getTelephone());
                    user.setLocation(updatedUser.getLocation());
                    user.setMail(updatedUser.getMail());
                    user.setPassword(updatedUser.getPassword());
                    return userRepository.save(user);
                })
                .orElse(null);
    }

    // DELETE
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Extra methods
    public List<User> getUsersByCompanyId(Long companyId) {
        return userRepository.findAll().stream()
            .filter(user -> user.getCompany() != null && user.getCompany().getId().equals(companyId))
            .toList();
    }

    public User addRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Role role = roleRepository.findById(roleId).orElse(null);
            if (role != null ){
                user.addRole(role);
                return userRepository.save(user);
            }
        }
        return null;
    }

}
