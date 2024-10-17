package com.parkour.kmp.api.history.domain;

import com.parkour.kmp.common.domain.TimestampEntity;
import com.parkour.kmp.api.medication.domain.Medication;
import com.parkour.kmp.api.user.domain.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "histories",
        indexes = {
            @Index(name = "idx_user_token", columnList = "user_token"),
            @Index(name = "idx_medication_id", columnList = "medication_id")
        }
)
public class History extends TimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_token", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medication_id", nullable = false)
    private Medication medication;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    protected History() {}

    public History(User user, Medication medication, LocalDate expiryDate) {
        this.user = user;
        this.medication = medication;
        this.expiryDate = expiryDate;
    }
}
