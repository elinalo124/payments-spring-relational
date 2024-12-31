package com.payments.relational.repository;

import com.payments.relational.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Optional<Promotion> findByCode(String code);
//    List<Promotion> findByCuitStoreAndValidityStartDateLessThanEqualAndValidityEndDateGreaterThanEqual(
//            String cuitStore, LocalDate validityStartDate, LocalDate validityEndDate);
    @Query("SELECT p FROM Promotion p WHERE p.cuitStore = :cuitStore AND " +
            "(p.validityEndDate <= :validityEndDate AND p.validityStartDate >= :validityStartDate)")
    List<Promotion> findValidPromotionsInRange(@Param("cuitStore") String cuitStore,
                                               @Param("validityStartDate") LocalDate validityStartDate,
                                               @Param("validityEndDate") LocalDate validityEndDate);
}
