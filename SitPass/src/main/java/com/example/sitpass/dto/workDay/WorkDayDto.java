package com.example.sitpass.dto.workDay;

import com.example.sitpass.model.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkDayDto {
    private Long id;
    private LocalDate validFrom;
    private DayOfWeek day;
    private LocalTime from;
    private LocalTime until;

}
