package com.example.team11.Controller;

import com.example.team11.DTO.SupplierContactDTO;
import com.example.team11.Entity.SupplierContact;
import com.example.team11.Service.SupplierContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/supplier-contacts")
public class SupplierContactController {

    @Autowired
    private SupplierContactService supplierContactService;

    @GetMapping
    public List<SupplierContactDTO> getAllSupplierContacts() {
        return supplierContactService.getAllSupplierContacts();
    }

    @GetMapping("/{id}")
    public SupplierContactDTO getSupplierContactById(@PathVariable Long id) {
        return supplierContactService.getSupplierContactById(id);
    }

    @PostMapping
    public ResponseEntity<?> createSupplierContact(@RequestBody SupplierContactDTO dto) {
        try {
            SupplierContact contact = supplierContactService.createSupplierContact(dto);
            return ResponseEntity.ok(contact);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSupplierContact(@PathVariable Long id, @RequestBody SupplierContactDTO dto) {
        try {
            SupplierContact contact = supplierContactService.updateSupplierContact(id, dto);
            return ResponseEntity.ok(contact);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplierContact(@PathVariable Long id) {
        try {
            supplierContactService.deleteSupplierContact(id);
            return ResponseEntity.ok("Supplier contact deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}