package com.example.sitpass.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer equipment;
    @Column(nullable = false)
    private Integer staff;
    @Column(nullable = false)
    private Integer hygiene;
    @Column(nullable = false)
    private Integer space;


}
