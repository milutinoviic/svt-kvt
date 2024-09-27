package com.example.sitpass.repository;

import com.example.sitpass.model.Manages;
import com.example.sitpass.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ManagesRepository extends JpaRepository<Manages,Long> {

    boolean existsByUserIdAndFacilityId(Long userId, Long facilityId);

    boolean existsByFacilityId(Long facilityId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Manages m WHERE m.facility.id = :facilityId")
    void deleteByFacilityId(@Param("facilityId") Long facilityId);

    @Query("SELECT m.facility.id FROM Manages m WHERE m.user.id = :userId")
    Long findFacilityIdByUserId(@Param("userId") Long userId);



}




