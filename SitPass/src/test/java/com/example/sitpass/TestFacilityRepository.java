package com.example.sitpass;

import com.example.sitpass.model.DayOfWeek;
import com.example.sitpass.model.Facility;
import com.example.sitpass.model.WorkDay;
import com.example.sitpass.repository.FacilityRepository;
import com.example.sitpass.repository.WorkDayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

@DataJpaTest
public class TestFacilityRepository {
    @Autowired
    FacilityRepository facilityRepository;

    @Autowired
    WorkDayRepository workDayRepository;
    @BeforeEach
    public void setUp(){
        Facility f = new Facility();
        f.setName("test name");
        f.setAddress("test address");
        f.setDescription("description for test");
        f.setCreatedAt(LocalDate.now());
        f.setCity("ccc");
        f.setTotalRating(2.0);
        f.setActive(true);
        WorkDay workDay = new WorkDay();
        workDay.setDay(DayOfWeek.SUNDAY);
        workDay.setUntil(LocalTime.now());
        workDay.setValidFrom(LocalDate.now());
        workDay.setFrom(LocalTime.now());
        workDayRepository.save(workDay);
        List<WorkDay> workDays = new ArrayList<>();
        workDays.add(workDay);
        f.setWorkDays(workDays);
        facilityRepository.save(f);
    }
    @Test
    void test2(){
        List<Facility> facilities = facilityRepository.searchFacilities("ccc",1.0,2.5);
        System.out.println(facilities);
        System.out.println("test");
    }

}
