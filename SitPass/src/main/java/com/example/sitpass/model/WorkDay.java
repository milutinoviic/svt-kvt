package com.example.sitpass.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkDay {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY )
    private Long id;

    @Column(nullable = false)
    private LocalDate validFrom;

    @Column(nullable = false)
    private DayOfWeek day;

    @Column(name="startTime",nullable = false)
    private LocalTime from;

    @Column(name="endTime",nullable = false)
    private LocalTime until;


}
