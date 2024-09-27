package com.example.sitpass.controller;

import com.example.sitpass.dto.discipline.DisciplineCreateDto;
import com.example.sitpass.dto.discipline.DisciplineDto;
import com.example.sitpass.service.implementation.DisciplineServiceImpl;
import com.example.sitpass.service.implementation.FacilityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplines")
@CrossOrigin("*")
public class DisciplineController {

    private final DisciplineServiceImpl disciplineService;


    @Autowired
    public DisciplineController(DisciplineServiceImpl disciplineService, FacilityService facilityService) {

        this.disciplineService = disciplineService;

    }

    @GetMapping
    public ResponseEntity<List<DisciplineDto>> getAllDisciplines() {
        List<DisciplineDto> disciplinesDto = disciplineService.getAllDisciplines();
        return new ResponseEntity<>(disciplinesDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplineDto> getDisciplineById(@PathVariable Long id) {
        DisciplineDto disciplineDto = disciplineService.getDisciplineByIdDto(id);
        if (disciplineDto != null) {
            return new ResponseEntity<>(disciplineDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<DisciplineDto> createDiscipline(@Valid @RequestBody DisciplineCreateDto discipline) {
            DisciplineDto createdDiscipline = disciplineService.createDiscipline(discipline);
            return new ResponseEntity<>(createdDiscipline, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplineDto> updateDiscipline(@PathVariable Long id, @RequestBody DisciplineCreateDto discipline) {

        DisciplineDto updatedDiscipline = disciplineService.updateDiscipline(id, discipline);
        return new ResponseEntity<>(updatedDiscipline, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DisciplineDto> deleteDiscipline(@PathVariable Long id) {
        DisciplineDto deletedDisciplineDto = disciplineService.deleteDiscipline(id);
        if (deletedDisciplineDto != null) {
            return new ResponseEntity<>(deletedDisciplineDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("disciplineFacility/{id}")
    public ResponseEntity<List<DisciplineDto>> findDisciplinesfromFacilityById(@PathVariable long id){
        List<DisciplineDto> disciplineDtos = disciplineService.findDisciplinesByFacilityId(id);
        if (disciplineDtos != null) {
            return new ResponseEntity<>(disciplineDtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
