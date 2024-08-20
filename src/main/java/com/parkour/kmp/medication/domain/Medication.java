package com.parkour.kmp.medication.domain;

import com.parkour.kmp.common.domain.TimestampEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.UUID;

@Entity
@Getter
@Table(name = "medications")
public class Medication extends TimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 제품명
    @Column(name = "item_name", nullable = false)
    private String itemName;

    // 설명 1. 효능: 이 약의 효능은 무엇입니까?
    @Column(name = "desc_effect")
    private String descEffect;

    // 설명 2. 사용법: 이 약은 어떻게 사용합니까?
    @Column(name = "desc_use_method")
    private String descUseMethod;

    // 설명 3. 주의사항 경고: 이 약을 사용하기 전에 반드시 알아야 할 내용은 무엇입니까?
    @Column(name = "desc_warning_before_usage")
    private String descWarningBeforeUsage;

    // 설명 4. 주의사항: 이 약의 사용상 주의사항은 무엇입니까?
    @Column(name = "desc_warning")
    private String descWarning;

    // 설명 5. 상호작용: 이 약을 사용하는 동안 주의해야 할 약 또는 음식은 무엇입니까?
    @Column(name = "desc_banned_combo")
    private String descBannedCombo;

    // 설명 6. 부작용: 이 약은 어떤 이상반응이 나타날 수 있습니까?
    @Column(name = "desc_side_effect")
    private String descSideEffect;

    // 설명 7. 보관법: 이 약은 어떻게 보관해야 합니까?
    @Column(name = "desc_manage_method")
    private String descManageMethod;

    protected Medication() {}

    public Medication(String itemName, String descEffect, String descUseMethod, String descWarningBeforeUsage, String descWarning, String descBannedCombo, String descSideEffect, String descManageMethod) {
        this.itemName = itemName;
        this.descEffect = descEffect;
        this.descUseMethod = descUseMethod;
        this.descWarningBeforeUsage = descWarningBeforeUsage;
        this.descWarning = descWarning;
        this.descBannedCombo = descBannedCombo;
        this.descSideEffect = descSideEffect;
        this.descManageMethod = descManageMethod;
    }

    @PrePersist
    public void prePersist() {
        String doesNotExist = "해당 정보가 존재하지 않습니다.";
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(this);
                if (value instanceof String && (value == null || ((String) value).trim().isEmpty())) {
                    field.set(this, doesNotExist);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
