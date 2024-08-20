package com.parkour.kmp.medcode.domain;

import com.parkour.kmp.common.domain.TimestampEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(indexes = @Index(name = "idx_item_seq", columnList = "item_seq"))
public class MedCode extends TimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "item_seq", nullable = false)
    private String itemSeq;

    @Column(name = "representative_code", nullable = false)
    private String representativeCode;

    @Column(name = "standard_code", nullable = false)
    private String standardCode;

    @Column(name = "product_code", nullable = false)
    private String productCode;

    @Column(name = "atc_code", nullable = false)
    private String atcCode;

    protected MedCode() {}

    public MedCode(String itemSeq, String representativeCode, String standardCode, String productCode, String atcCode) {
        this.itemSeq = itemSeq;
        this.representativeCode = representativeCode;
        this.standardCode = standardCode;
        this.productCode = productCode;
        this.atcCode = atcCode;
    }
}
