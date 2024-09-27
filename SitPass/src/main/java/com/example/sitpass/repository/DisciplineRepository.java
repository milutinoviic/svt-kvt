package com.example.sitpass.repository;

import com.example.sitpass.model.Discipline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DisciplineRepository extends JpaRepository<Discipline,Long> {

    @Query("SELECT f.disciplines FROM Facility f WHERE f.id = :id")
    List<Discipline> findDisciplinesByFacillty_Id(Long id);

    @Query("SELECT d FROM Discipline d JOIN d.facilities f WHERE f.id = :facilityId")
    List<Discipline> findDisciplinesByFacilityId(@Param("facilityId") Long facilityId);

}
