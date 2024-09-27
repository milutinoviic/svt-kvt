package com.example.sitpass.dto.workDay;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WorkDayRequest {

        @NotNull
        private LocalDate validFrom;
}
