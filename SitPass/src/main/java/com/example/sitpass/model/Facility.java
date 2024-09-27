package com.example.sitpass.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false)
    private String description;
    private LocalDate createdAt;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;

    private Double totalRating;
    @Column(nullable = false)
    private boolean active;
    @OneToMany(cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private List<WorkDay> workDays;
    @OneToMany(mappedBy = "facility",cascade = CascadeType.ALL)
    private List<Image> images;
    @ManyToMany(mappedBy = "facilities", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private List<Discipline> disciplines;

    @Override
    public String toString() {
        return "Facility{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAl=" + createdAt +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", totalRating=" + totalRating +
                ", active=" + active +
                '}';
    }
}
