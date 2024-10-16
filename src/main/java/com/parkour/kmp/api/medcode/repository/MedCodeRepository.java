package com.parkour.kmp.api.medcode.repository;

import com.parkour.kmp.api.medcode.domain.MedCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedCodeRepository extends JpaRepository<MedCode, Long> {
    void deleteAll();
    Optional<MedCode> findByItemSeq(String itemSeq);

}
