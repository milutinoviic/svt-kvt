package com.example.sitpass.repository;

import com.example.sitpass.model.Rate;
import com.example.sitpass.model.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkDayRepository extends JpaRepository<WorkDay,Long> {



}
