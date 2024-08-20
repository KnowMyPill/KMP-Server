package com.parkour.kmp.medcode.repository;

import com.parkour.kmp.medcode.domain.MedCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedCodeRepository extends JpaRepository<MedCode, Long> {
    void deleteAll();
}
