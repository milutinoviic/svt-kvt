package com.example.sitpass.dto.workDay;

import com.example.sitpass.model.DayOfWeek;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkDayCreateDto {

    @NotNull
    private LocalDate validFrom;
    @NotNull
    private DayOfWeek day;
    @NotNull
    private LocalTime from;
    @NotNull
    private LocalTime until;
}
