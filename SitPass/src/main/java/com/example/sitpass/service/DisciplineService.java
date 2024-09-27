package com.example.sitpass.service;

import com.example.sitpass.dto.discipline.DisciplineCreateDto;
import com.example.sitpass.dto.discipline.DisciplineDto;
import com.example.sitpass.exceptions.FacilityNotFoundException;
import com.example.sitpass.model.Discipline;
import com.example.sitpass.model.Facility;


import java.util.List;

public interface DisciplineService {

    List<DisciplineDto> getAllDisciplines();

    Discipline getDisciplineById(Long id);

    List<DisciplineDto> findByFacilityId(long facilityId);

    DisciplineDto getDisciplineByIdDto(Long id);

    DisciplineDto convertDisciplineToDto(Discipline discipline);

    Discipline convertDtoToDiscipline(DisciplineCreateDto discipline);

    DisciplineDto createDiscipline(DisciplineCreateDto disciplineCreateDto) ;

    DisciplineDto updateDiscipline(Long id, DisciplineCreateDto disciplineDetails);

    DisciplineDto deleteDiscipline(Long id);

    List<DisciplineDto> findDisciplinesByFacilityId(long id);

    void updateDiscipline(Discipline discipline);
}
