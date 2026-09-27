package com.example.planion_backend.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.planion_backend.user.UserRepository;
import com.example.planion_backend.user.User;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CRUD OPERATIONS
    // CREATE
    public User createUser(User user) {
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

}
