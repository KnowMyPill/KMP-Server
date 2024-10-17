package com.parkour.kmp.api.medication.repository;

import com.parkour.kmp.api.medication.domain.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    boolean existsByItemSeq(String itemSeq);
    Optional<Medication> findByItemSeq(String itemSeq);
}
