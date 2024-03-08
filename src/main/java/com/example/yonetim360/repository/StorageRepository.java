package com.example.yonetim360.repository;

import com.example.yonetim360.entities.ImageData;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData, Long> {
    @Transactional
    Optional<ImageData> findByName(String fileName);
}
