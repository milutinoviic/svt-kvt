package com.example.sitpass.service.implementation;

import com.example.sitpass.dto.discipline.DisciplineDto;
import com.example.sitpass.dto.facility.FacilityCreateDto;
import com.example.sitpass.dto.facility.FacilityDto;
import com.example.sitpass.dto.image.ImageFacilityDto;
import com.example.sitpass.dto.workDay.WorkDayCreateDto;
import com.example.sitpass.dto.workDay.WorkDayDto;
import com.example.sitpass.exceptions.FacilityNotFoundException;
import com.example.sitpass.model.*;
import com.example.sitpass.repository.FacilityRepository;
import com.example.sitpass.repository.ManagesRepository;
import com.example.sitpass.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacilityService implements com.example.sitpass.service.FacilltyService {
    private final ImageService imageService;
    private final WorkDayService workDayService;

    private final DisciplineServiceImpl disciplineService;
    private final FacilityRepository facilityRepository;

    private final ReviewRepository reviewRepository;

    private ManagesRepository managerRepository;


    @Autowired
    public FacilityService(ImageService imageService, WorkDayService workDayService, DisciplineServiceImpl disciplineServise, FacilityRepository facilityRepository, ReviewRepository reviewRepository,ManagesRepository managerRepository) {

        this.imageService = imageService;
        this.workDayService = workDayService;
        this.disciplineService = disciplineServise;
        this.facilityRepository = facilityRepository;
        this.reviewRepository = reviewRepository;
        this.managerRepository = managerRepository;
    }
    @Override
    public FacilityDto createFacility(FacilityCreateDto facilityDetails) {
        Facility facility = new Facility();

        facility.setName(facilityDetails.getName());
        facility.setDescription(facilityDetails.getDescription());
        facility.setCreatedAt(LocalDate.now());
        facility.setAddress(facilityDetails.getAddress());
        facility.setCity(facilityDetails.getCity());
        facility.setTotalRating(10d);
        facility.setActive(false);
        List<WorkDay> workDays = new ArrayList<>();
        for (WorkDayCreateDto workDayCreateDto : facilityDetails.getWorkDays()) {
            WorkDay workDay = workDayService.convertDtoToWorkDay(workDayCreateDto);
            workDays.add(workDay);
        }
        facility.setWorkDays(workDays);

        List<Discipline> disciplines = new ArrayList<>();
        for(Long disciplineId: facilityDetails.getDisciplinesIds()){
            disciplines.add(disciplineService.getDisciplineById(disciplineId));
        }

        facility.setDisciplines(disciplines);
        facilityRepository.save(facility);

        for(Discipline discipline: disciplines){
            discipline.getFacilities().add(facility);
            disciplineService.updateDiscipline(discipline);
        }

        FacilityDto facilityDto = convertToDto(facility);

        return facilityDto;

    }
    public void updateFacilityAttributeActive(Long facilityId, Long userId) {
        boolean exists = managerRepository.existsByUserIdAndFacilityId(userId,facilityId);
        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new RuntimeException("Facility not found"));
        facility.setActive(exists);
        facilityRepository.save(facility);
    }

    @Override
    public FacilityDto convertToDto (Facility facilty) {
        FacilityDto facilityDto = new FacilityDto();
        facilityDto.setId(facilty.getId());
        facilityDto.setName(facilty.getName());
        facilityDto.setDescription(facilty.getDescription());
        facilityDto.setCreatedAt(facilty.getCreatedAt());
        facilityDto.setAddress(facilty.getAddress());
        facilityDto.setCity(facilty.getCity());
        double avrageRating = gradeNumber(facilty.getId());
        facilty.setTotalRating(avrageRating);
        facilityDto.setTotalRating(facilty.getTotalRating());
        facilityDto.setActive(facilty.isActive());

        List<WorkDayDto> workdays = new ArrayList<>();
        for (WorkDay workDay : facilty.getWorkDays()) {
            workdays.add(workDayService.convertToDto(workDay));
        }

        facilityDto.setWorkDays(workdays);
        List<ImageFacilityDto> facilityImages = imageService.findByFacilityId(facilty.getId());
        facilityDto.setImages(facilityImages);
        List<DisciplineDto> disciplineDtos = disciplineService.findByFacilityId(facilty.getId());
        facilityDto.setDisciplines(disciplineDtos);

        return  facilityDto;

    }
    @Override
    public List<FacilityDto> getAllFacilities() {
        List<FacilityDto> facilityDtos = new ArrayList<>();
        for (Facility facilty : facilityRepository.findAll()) {
            FacilityDto faciltyGetDto = convertToDto(facilty);
            facilityDtos.add(faciltyGetDto);


        }
        return facilityDtos;
    }


    public List<FacilityDto> getAllInactiveFacilities() {
        List<FacilityDto> facilityDtos = new ArrayList<>();
        for (Facility facilty : facilityRepository.findAllByActiveFalse()) {
            FacilityDto faciltyGetDto = convertToDto(facilty);
            facilityDtos.add(faciltyGetDto);


        }
        return facilityDtos;
    }

    @Override
    public Facility getFacilityById(long id) {
        Optional<Facility> facillty = facilityRepository.findById(id);
        return facillty.orElse(null);
    }
    @Override
    public FacilityDto getFacilityByIdDto(long id) {
        Facility facility = getFacilityById(id);
        FacilityDto faciltyGetDto = convertToDto(facility);

        return faciltyGetDto;

    }
    @Override
    public FacilityDto updateFacility(Long id, FacilityCreateDto facilityDetails) {
        Optional<Facility> facilityOptional = facilityRepository.findById(id);
        if (facilityOptional.isEmpty()) {
            return null;
        }
        Facility facility = facilityOptional.get();
        facility.setName(facilityDetails.getName());
        facility.setDescription(facilityDetails.getDescription());
        facility.setAddress(facilityDetails.getAddress());
        facility.setCity(facilityDetails.getCity());
        facility.setActive(facilityDetails.isActive());
        List<WorkDay> workDays = new ArrayList<>();
        for (WorkDayCreateDto workDayCreateDto : facilityDetails.getWorkDays()) {
            WorkDay workDay = workDayService.convertDtoToWorkDay(workDayCreateDto);
            workDays.add(workDay);
        }
        facility.setWorkDays(workDays);



        List<Long> disciplineIdsFromFrontend = facilityDetails.getDisciplinesIds();


        List<Discipline> currentDisciplines = facility.getDisciplines();
        List<Discipline> updatedDisciplines = new ArrayList<>();

        for (Discipline discipline : currentDisciplines) {
            if (disciplineIdsFromFrontend.contains(discipline.getId())) {

                updatedDisciplines.add(discipline);
            } else {

                discipline.getFacilities().remove(facility);
                disciplineService.updateDiscipline(discipline);
            }
        }

        for (Long disciplineId : disciplineIdsFromFrontend) {
            boolean alreadyExists = updatedDisciplines.stream()
                    .anyMatch(discipline -> discipline.getId().equals(disciplineId));

            if (!alreadyExists) {
                Discipline newDiscipline = disciplineService.getDisciplineById(disciplineId);
                updatedDisciplines.add(newDiscipline);
                newDiscipline.getFacilities().add(facility);
                disciplineService.updateDiscipline(newDiscipline);
            }
        }

        facility.setDisciplines(updatedDisciplines);

        facilityRepository.save(facility);
        FacilityDto facilityDto = convertToDto(facility);
        return facilityDto;
    }
    @Override
    public FacilityDto deleteFacility(long id) {
        Optional<Facility> facilityOptional = facilityRepository.findById(id);
        if (facilityOptional.isEmpty()) {
            return null;
        }
        facilityRepository.deleteById(id);
        FacilityDto faciltyDto = convertToDto(facilityOptional.get());

        return faciltyDto;
    }

    @Override
    public List<WorkDayDto> findWorkDaysByFacilityId(long facilityId) {

        List<WorkDayDto> workDayDtos = new ArrayList<>();

        for( DayOfWeek dayOfWeek: DayOfWeek.values()){
            WorkDay result = findNewestWorkDayByFaciltyId(facilityId, dayOfWeek);
//            WorkDay result = null;
            if(result != null){
                workDayDtos.add(workDayService.convertToDto(result));
            }
        }

        return workDayDtos;
    }

    public List<WorkDay> findWorkDaysByFacilityId(Long facilityId) {

        List<WorkDay> workDays = new ArrayList<>();

        for( DayOfWeek dayOfWeek: DayOfWeek.values()){
            WorkDay result = findNewestWorkDayByFaciltyId(facilityId, dayOfWeek);
            if(result != null){
                workDays.add(result);
            }
        }

        return workDays;
    }

    @Override
    public List<FacilityDto> searchFacilities(Optional<String> city, Optional<Long> disciplineId, Optional<Double> fromRate, Optional<Double> toRate, Optional<LocalTime> fromWorkTime, Optional<LocalTime> toWorkTime) {
        List<Facility> facilities = facilityRepository.searchFacilities(city.orElse(null), fromRate.orElse(null), toRate.orElse(null));
        if(disciplineId.isPresent()){
            Long searchDisciplineId = disciplineId.get();
            facilities.removeIf(facility -> facility.getDisciplines().stream().noneMatch(discipline -> discipline.getId().equals(searchDisciplineId)));
        }
        Iterator<Facility> iterator = facilities.iterator();
        while (iterator.hasNext()) {
            Facility facility = iterator.next();
            List<WorkDay> currentWorkDays = findWorkDaysByFacilityId(facility.getId());

            if (fromWorkTime.isPresent()) {
                if (currentWorkDays.stream().noneMatch(workDay ->
                        workDay.getDay().toString().equals(LocalDate.now().getDayOfWeek().toString()) &&
                                workDay.getFrom().isBefore(fromWorkTime.get()))) {
                    iterator.remove();
                    continue;
                }
            }

            if (toWorkTime.isPresent()) {
                if (currentWorkDays.stream().noneMatch(workDay ->
                        workDay.getDay().toString().equals(LocalDate.now().getDayOfWeek().toString()) &&
                                workDay.getUntil().isAfter(toWorkTime.get()))) {
                    iterator.remove();
                }
            }
        }


        return facilities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<WorkDayDto> findAllWorkDaysByFacilityId(long facilityId) {

        List<WorkDayDto> workDayDtos = new ArrayList<>();
        List<WorkDay> workDays = facilityRepository.findWorkDaysByFacilityId(facilityId);

        for( WorkDay workDay: workDays){
            workDayDtos.add(workDayService.convertToDto(workDay));

        }

        return workDayDtos;
    }

    public double gradeNumber(Long id){
        List<Review> reviews = reviewRepository.findByFacilityIdAndActiveTrue(id);
        int count=0;

        double totalSum = 0.0;
        for (Review review : reviews) {
            count++;
            Rate rate = review.getRate();
            double averageRate = (rate.getEquipment() + rate.getStaff() + rate.getHygiene() + rate.getSpace());
            totalSum += averageRate;
        }
        if(count == 0){
            return 10;
        }
        count=count*4;


        return totalSum/count;

    }










    public WorkDay findNewestWorkDayByFaciltyId(Long facilityId, DayOfWeek dayOfWeek) {
        LocalDate todayDate = LocalDate.now();
        DayOfWeek currentDay =  DayOfWeek.valueOf(todayDate.getDayOfWeek().toString());
        int diff =  dayOfWeek.ordinal() - currentDay.ordinal();
        int daysToAdd;
        if(diff >= 0){
            daysToAdd = diff;
        }else{
            daysToAdd = 7 + diff;
        }
        LocalDate untilDate = todayDate.plusDays(daysToAdd);
        List<WorkDay> results = facilityRepository.findSortedWorkDayByFacilityAndDayOfWeek(facilityId, dayOfWeek, untilDate).orElse(null);
        if(results == null || results.isEmpty()){return null;}
        return results.get(0);
    }

    public WorkDayDto addWorkDayToFacility(Long facilityId,WorkDayCreateDto workDayDto) throws FacilityNotFoundException{

        Facility facility = facilityRepository.findById(facilityId).orElse(null);
        if(facility == null){
            throw new FacilityNotFoundException("Faciity Not Found");
        }
        WorkDay workDay =  workDayService.convertDtoToWorkDay(workDayDto);
        facility.getWorkDays().add(workDay);
        facilityRepository.save(facility);

        return workDayService.convertToDto(workDay);
    }




    public WorkDayDto findAndRemoveWorkDayByDate(Long facilityId, LocalDate date) throws FacilityNotFoundException{
        Facility facility = facilityRepository.findById(facilityId).orElse(null);

        WorkDay workDayToRemove = null;

        for (WorkDay workDay : facility.getWorkDays()) {
            if (workDay.getValidFrom().equals(date)) {
                workDayToRemove = workDay;
                break;
            }
        }

        if (workDayToRemove == null) {
            throw new FacilityNotFoundException("WorkDay not found");

        }


        facility.getWorkDays().remove(workDayToRemove);

        facilityRepository.save(facility);

        return workDayService.convertToDto(workDayToRemove);
    }


    public List<FacilityDto> searchForHomePage(Optional<String> city, Optional<LocalDate> day) {
        List<Facility> facilities;

        if (city.isPresent()) {
            facilities = facilityRepository.findByCity(city.get());
        } else if (day.isPresent()) {
            facilities = facilityRepository.findByCreatedAt(day.get());
            // Filtriranje za najbliže datume
            facilities.sort(Comparator.comparing(Facility::getCreatedAt));
        } else {
            facilities = facilityRepository.findPopularFacilities();
        }

        // Uzimanje prvih 4 objekta
        return facilities.stream()
                .limit(4)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


}
