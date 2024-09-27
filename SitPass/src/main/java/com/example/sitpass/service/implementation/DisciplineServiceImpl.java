package com.example.sitpass.service.implementation;

import com.example.sitpass.dto.discipline.DisciplineCreateDto;
import com.example.sitpass.dto.discipline.DisciplineDto;
import com.example.sitpass.model.Discipline;
import com.example.sitpass.repository.DisciplineRepository;
import com.example.sitpass.service.DisciplineService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DisciplineServiceImpl implements DisciplineService {


    private final DisciplineRepository disciplineRepository;

    public DisciplineServiceImpl(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }


    @Override
    public List<DisciplineDto> getAllDisciplines() {
        List<DisciplineDto> disciplineDtos = new ArrayList<>();
        for (Discipline discipline : disciplineRepository.findAll()) {
            DisciplineDto disciplineDto = convertDisciplineToDto(discipline);
            disciplineDtos.add(disciplineDto);
        }
        return disciplineDtos;
    }

    @Override
    public Discipline getDisciplineById(Long id) {
        Optional<Discipline> discipline = disciplineRepository.findById(id);
        return discipline.orElse(null);
    }

    @Override
    public List<DisciplineDto> findByFacilityId(long facilityId) {
        List<DisciplineDto> disciplineDto = new ArrayList<>();
        for(Discipline discipline:disciplineRepository.findDisciplinesByFacilityId(facilityId)){
            DisciplineDto disciplineGetDto= convertDisciplineToDto(discipline);
            disciplineDto.add(disciplineGetDto);


        }
        return disciplineDto;
    }

    @Override
    public DisciplineDto getDisciplineByIdDto(Long id) {
        Optional<Discipline> discipline = disciplineRepository.findById(id);
        if (discipline.isEmpty()) {
            return null;
        }

        DisciplineDto disciplineDto = convertDisciplineToDto(discipline.get());
        return disciplineDto;

    }

    @Override
    public DisciplineDto convertDisciplineToDto(Discipline discipline) {
        DisciplineDto disciplineDto = new DisciplineDto();
        disciplineDto.setId(discipline.getId());
        disciplineDto.setName(discipline.getName());
        return disciplineDto;
    }


    @Override
    public Discipline convertDtoToDiscipline(DisciplineCreateDto discipline){
        Discipline disciplineNew = new Discipline();
        disciplineNew.setName(discipline.getName());
        return disciplineNew;
    }

    @Override
    public DisciplineDto createDiscipline(DisciplineCreateDto disciplineCreateDto)  {
        Discipline discipline = new Discipline();
        discipline.setName(disciplineCreateDto.getName());
        disciplineRepository.save(discipline);

        DisciplineDto disciplineDto = convertDisciplineToDto(discipline); //convertDisciplineToDto

        return disciplineDto;



    }

    @Override
    public DisciplineDto updateDiscipline(Long id, DisciplineCreateDto disciplineDetails) {
        Optional<Discipline> disciplineOptional = disciplineRepository.findById(id);
        if (disciplineOptional.isEmpty()){
            return null;
        }
        Discipline discipline = disciplineOptional.get();
        discipline.setName(disciplineDetails.getName());

        disciplineRepository.save(discipline);


        DisciplineDto disciplineDto = convertDisciplineToDto(discipline);

        return disciplineDto;

    }

    @Override
    public DisciplineDto deleteDiscipline(Long id) {
        Optional<Discipline> discipline = disciplineRepository.findById(id);
        if (discipline.isEmpty()){
            return null;
        }
        disciplineRepository.deleteById(id);
        DisciplineDto disciplineDto = convertDisciplineToDto(discipline.get());
        return disciplineDto;
    }
    @Override
    public List<DisciplineDto> findDisciplinesByFacilityId(long id) {
        List<Discipline> disciplines=disciplineRepository.findDisciplinesByFacillty_Id(id);

        List<DisciplineDto> disciplinesDtos = new ArrayList<>();

        for (Discipline discipline : disciplines) {
            disciplinesDtos.add(convertDisciplineToDto(discipline));
        }

        return disciplinesDtos;

    }

    @Override
    public void updateDiscipline(Discipline discipline) {
        disciplineRepository.save(discipline);
    }


}
