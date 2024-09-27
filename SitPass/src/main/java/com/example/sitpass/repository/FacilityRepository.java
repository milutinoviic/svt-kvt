package com.example.sitpass.repository;

import com.example.sitpass.model.DayOfWeek;
import com.example.sitpass.model.Facility;
import com.example.sitpass.model.WorkDay;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface FacilityRepository extends JpaRepository<Facility,Long> {


    @Query("SELECT f.workDays FROM Facility f WHERE f.id = :id")
    List<WorkDay> findWorkDaysByFacilityId(@Param("id") Long id);


    @Query("SELECT wd FROM Facility f " +
            "JOIN f.workDays wd " +
            "WHERE f.id = :facilityId " +
            "AND wd.day = :dayOfWeek " +
            "AND wd.validFrom <= :untilDate " +
            "ORDER BY wd.validFrom DESC")
    Optional<List<WorkDay>> findSortedWorkDayByFacilityAndDayOfWeek(
            @Param("facilityId") Long facilityId,
            @Param("dayOfWeek") DayOfWeek dayOfWeek,
            @Param("untilDate") LocalDate untilDate);




    @Query("SELECT f FROM Facility f  WHERE f.active = true AND (:city IS NULL OR LOWER(CAST(f.city AS text)) = LOWER(CAST(:city AS text))) AND ((:fromRate is null or f.totalRating >= :fromRate) AND (:toRate is null or f.totalRating <= :toRate))")
    List<Facility> searchFacilities(@Param("city") String city, @Param("fromRate") Double fromRate, @Param("toRate") Double toRate);


    Optional<Facility> findByIdAndWorkDaysValidFrom(Long facilityId, LocalDate validFrom);

    List<Facility> findAllByActiveFalse();


    @Query("SELECT f FROM Facility f WHERE (:city IS NULL OR f.city = :city) " +
            "AND (f.active = true) ORDER BY f.totalRating DESC")
    List<Facility> findByCity(@Param("city") String city);

    @Query("SELECT f FROM Facility f WHERE f.createdAt = :date " +
            "ORDER BY f.createdAt ASC")
    List<Facility> findByCreatedAt(@Param("date") LocalDate date);

    @Query("SELECT f FROM Facility f WHERE f.active = true ORDER BY f.totalRating DESC")
    List<Facility> findPopularFacilities();




}
