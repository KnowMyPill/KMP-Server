package com.parkour.kmp.api.medcode.domain;

import com.parkour.kmp.common.domain.TimestampEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "med_codes", indexes = @Index(name = "idx_item_seq", columnList = "item_seq"))
public class MedCode extends TimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "item_seq", nullable = false)
    private String itemSeq;

    @Column(name = "code_representative", nullable = false)
    private String codeRepresentative;

    @Column(name = "code_standard", nullable = false)
    private String codeStandard;

    protected MedCode() {}

    public MedCode(String itemSeq, String codeRepresentative, String codeStandard) {
        this.itemSeq = itemSeq;
        this.codeRepresentative = codeRepresentative;
        this.codeStandard = codeStandard;
    }
}
