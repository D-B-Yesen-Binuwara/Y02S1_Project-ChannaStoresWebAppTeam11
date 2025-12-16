package com.example.team11.Service;

import com.example.team11.DTO.SupplierContactDTO;
import com.example.team11.Entity.SupplierContact;
import com.example.team11.Repository.SupplierContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierContactService {

    @Autowired
    private SupplierContactRepository supplierContactRepository;

    public List<SupplierContactDTO> getAllSupplierContacts() {
        return supplierContactRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SupplierContactDTO getSupplierContactById(Long id) {
        SupplierContact contact = supplierContactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier contact not found"));
        return convertToDTO(contact);
    }

    public SupplierContact createSupplierContact(SupplierContactDTO dto) {
        SupplierContact contact = new SupplierContact();
        contact.setName(dto.getName());
        contact.setEmail(dto.getEmail());
        contact.setPhoneNumber(dto.getPhoneNumber());
        contact.setProductType(dto.getProductType());
        return supplierContactRepository.save(contact);
    }

    public SupplierContact updateSupplierContact(Long id, SupplierContactDTO dto) {
        SupplierContact contact = supplierContactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier contact not found"));
        
        contact.setName(dto.getName());
        contact.setEmail(dto.getEmail());
        contact.setPhoneNumber(dto.getPhoneNumber());
        contact.setProductType(dto.getProductType());
        
        return supplierContactRepository.save(contact);
    }

    public void deleteSupplierContact(Long id) {
        SupplierContact contact = supplierContactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier contact not found"));
        supplierContactRepository.delete(contact);
    }

    private SupplierContactDTO convertToDTO(SupplierContact contact) {
        return new SupplierContactDTO(
            contact.getId(),
            contact.getName(),
            contact.getEmail(),
            contact.getPhoneNumber(),
            contact.getProductType()
        );
    }
}