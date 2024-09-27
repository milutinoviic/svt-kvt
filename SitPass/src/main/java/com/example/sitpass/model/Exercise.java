package com.example.sitpass.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

//Rezervisanje odlaska
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="startDate")
    private LocalDateTime from;
    @Column(name="endDate")
    private LocalDateTime until;
    @ManyToOne
    private User user;
    @ManyToOne
    private Facility facility;


}
