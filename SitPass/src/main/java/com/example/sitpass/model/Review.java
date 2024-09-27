package com.example.sitpass.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate createdAt;
    private Integer exerciseCount;
    private boolean hidden;
    private boolean active;
    @ManyToOne
    private Facility facility;
    @ManyToOne
    private User user;
    @OneToOne
    private Rate rate;
    @OneToOne
    private Comment comment;
}
