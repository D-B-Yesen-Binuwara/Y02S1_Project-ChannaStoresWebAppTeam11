package com.example.team11.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.team11.Entity.SupplierContact;

public interface SupplierContactRepository extends JpaRepository<SupplierContact, Long> {
}