package com.example.team11.Service;

import com.example.team11.DTO.AdminDTO;
import com.example.team11.Entity.Admin;
import com.example.team11.Entity.User;
import com.example.team11.Repository.AdminRepository;
import com.example.team11.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    // Get all admins as DTOs
    public List<AdminDTO> getAllAdmins() {
        return adminRepository.findAll().stream()
                .map(admin -> new AdminDTO(admin.getId(), admin.getUser().getEmail()))
                .collect(Collectors.toList());
    }

    // Get a single admin by ID as a DTO
    public AdminDTO getAdminById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        AdminDTO dto = new AdminDTO(admin.getId(), admin.getUser().getEmail());
        dto.setUsername(admin.getUser().getUsername());
        dto.setPhoneNumber(admin.getPhoneNumber());
        return dto;
    }

    // Create a new admin (linking to an existing user)
    public Admin createAdmin(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!user.getRole().equalsIgnoreCase("admin")) {
            throw new RuntimeException("User role must be admin");
        }
        Admin admin = new Admin();
        admin.setUser(user);
        return adminRepository.save(admin);
    }

    // Update admin profile
    public Admin updateAdmin(Long id, AdminDTO adminDTO) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        
        User user = admin.getUser();
        if (adminDTO.getUsername() != null) {
            user.setUsername(adminDTO.getUsername());
        }
        if (adminDTO.getEmail() != null) {
            user.setEmail(adminDTO.getEmail());
        }
        if (adminDTO.getPhoneNumber() != null) {
            admin.setPhoneNumber(adminDTO.getPhoneNumber());
        }
        
        userRepository.save(user);
        return adminRepository.save(admin);
    }

    // Delete an admin by ID
    public void deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        adminRepository.delete(admin);
    }
}
