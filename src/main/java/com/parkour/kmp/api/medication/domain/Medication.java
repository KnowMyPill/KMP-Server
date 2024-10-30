package com.parkour.kmp.api.medication.domain;

import com.parkour.kmp.api.common.domain.TimestampEntity;
import com.parkour.kmp.api.history.domain.History;
import jakarta.persistence.*;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "medications")
public class Medication extends TimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="item_seq", nullable = false)
    private String itemSeq;

    // 제품명
    @Column(name = "item_name", nullable = false)
    private String itemName;

    // 설명 1. 효능: 이 약의 효능은 무엇입니까?
    @Column(name = "description_effect", length = 500)
    private String descEffect;

    // 설명 2. 사용법: 이 약은 어떻게 사용합니까?
    @Column(name = "description_use_method", length = 500)
    private String descUseMethod;

    // 설명 3. 주의사항 경고: 이 약을 사용하기 전에 반드시 알아야 할 내용은 무엇입니까?
    @Column(name = "description_warning_before_usage", length = 500)
    private String descWarningBeforeUsage;

    // 설명 4. 주의사항: 이 약의 사용상 주의사항은 무엇입니까?
    @Column(name = "description_warning", length = 500)
    private String descWarning;

    // 설명 5. 상호작용: 이 약을 사용하는 동안 주의해야 할 약 또는 음식은 무엇입니까?
    @Column(name = "description_banned_combo", length = 500)
    private String descBannedCombo;

    // 설명 6. 부작용: 이 약은 어떤 이상반응이 나타날 수 있습니까?
    @Column(name = "description_side_effect", length = 500)
    private String descSideEffect;

    // 설명 7. 보관법: 이 약은 어떻게 보관해야 합니까?
    @Column(name = "description_manage_method", length = 500)
    private String descManageMethod;

    @OneToMany(mappedBy = "medication", cascade = CascadeType.ALL)
    private List<History> histories = new ArrayList<>();

    protected Medication() {}

    public Medication(String itemSeq, String itemName, String descEffect, String descUseMethod, String descWarningBeforeUsage, String descWarning, String descBannedCombo, String descSideEffect, String descManageMethod) {
        this.itemSeq = itemSeq;
        this.itemName = itemName;
        this.descEffect = descEffect;
        this.descUseMethod = descUseMethod;
        this.descWarningBeforeUsage = descWarningBeforeUsage;
        this.descWarning = descWarning;
        this.descBannedCombo = descBannedCombo;
        this.descSideEffect = descSideEffect;
        this.descManageMethod = descManageMethod;
        this.histories = new ArrayList<>();
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

    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", itemSeq='" + itemSeq + '\'' +
                ", itemName='" + itemName + '\'' +
                ", descEffect='" + descEffect + '\'' +
                ", descUseMethod='" + descUseMethod + '\'' +
                ", descWarningBeforeUsage='" + descWarningBeforeUsage + '\'' +
                ", descWarning='" + descWarning + '\'' +
                ", descBannedCombo='" + descBannedCombo + '\'' +
                ", descSideEffect='" + descSideEffect + '\'' +
                ", descManageMethod='" + descManageMethod + '\'' +
                ", histories=" + histories +
                '}';
    }
}
