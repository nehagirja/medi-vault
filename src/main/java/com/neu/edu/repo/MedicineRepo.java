package com.neu.edu.repo;

import com.neu.edu.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MedicineRepo extends JpaRepository<Medicine, UUID> {
    Medicine findByName(String name);

    List<Medicine> findByUsers_Id(UUID id);
}
