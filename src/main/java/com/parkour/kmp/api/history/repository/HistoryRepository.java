package com.parkour.kmp.api.history.repository;

import com.parkour.kmp.api.history.domain.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query("SELECT h FROM History h JOIN FETCH h.user u JOIN FETCH h.medication m WHERE u.token = :token")
    Page<History> findAllByUser(@Param("token") String token, Pageable pageable);
}
