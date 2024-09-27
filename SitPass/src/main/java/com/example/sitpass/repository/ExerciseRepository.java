package com.example.sitpass.repository;

import com.example.sitpass.model.Exercise;
import com.example.sitpass.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise,Long> {

//    public List<Exercise> findExerciseByFacilityIdAndUserId(Long facilityId, Long userId);

//    long countByFacilityIdAndUserIdAndDateBefore(Long facilityId, Long userId, LocalDateTime dateTime);

    @Query("SELECT COUNT(e) FROM Exercise e WHERE e.facility.id = :facilityId AND e.user.id = :userId AND e.until <= :dateTime")
    Long countByFacilityIdAndUserIdAndDateBefore(@Param("facilityId") Long facilityId, @Param("userId") Long userId, @Param("dateTime") LocalDateTime dateTime);


    @Query("SELECT e FROM Exercise e WHERE e.user.id = :userId")
    List<Exercise> findAllByUserId(@Param("userId") Long userId);
}
