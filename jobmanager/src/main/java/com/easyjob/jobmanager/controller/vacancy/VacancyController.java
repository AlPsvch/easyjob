package com.easyjob.jobmanager.controller.vacancy;

import com.easyjob.jobmanager.dto.vacancy.VacancyDto;
import com.easyjob.jobmanager.entity.vacancy.Vacancy;
import com.easyjob.jobmanager.entity.vacancy.VacancyPage;
import com.easyjob.jobmanager.service.vacancy.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/vacancy")
public class VacancyController {

    private final VacancyService vacancyService;

    @Autowired
    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<VacancyDto>> getAllVacancies() {
        List<Vacancy> vacancies = vacancyService.findAllVacancies();
        List<VacancyDto> vacanciesDto = vacancies.stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(vacanciesDto, HttpStatus.OK);
    }

    @GetMapping(path = "/find")
    public ResponseEntity<Page<Vacancy>> getVacancies(VacancyPage vacancyPage) {
        Page<Vacancy> vacancies = vacancyService.findVacancies(vacancyPage);
        return new ResponseEntity<>(vacancies, HttpStatus.OK);
    }

    @GetMapping(path = "/find/{id}")
    public ResponseEntity<VacancyDto> getVacancy(@PathVariable("id") Long id) {
        Vacancy vacancy = vacancyService.findVacancy(id);
        VacancyDto vacancyDto = convertToDto(vacancy);
        return new ResponseEntity<>(vacancyDto, HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<VacancyDto> addVacancy(@RequestBody Vacancy vacancy) {
        Vacancy addedVacancy = vacancyService.addVacancy(vacancy);
        VacancyDto addedVacancyDto = convertToDto(addedVacancy);
        return new ResponseEntity<>(addedVacancyDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<VacancyDto> updateVacancy(@RequestBody Vacancy vacancy) {
        Vacancy updatedVacancy = vacancyService.updateVacancy(vacancy);
        VacancyDto updatedVacancyDto = convertToDto(updatedVacancy);
        return new ResponseEntity<>(updatedVacancyDto, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteVacancy(@PathVariable("id") Long id) {
        vacancyService.deleteVacancy(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private VacancyDto convertToDto(Vacancy vacancy) {
        VacancyDto vacancyDto = new VacancyDto();

        vacancyDto.setId(vacancy.getId());
        vacancyDto.setName(vacancy.getName());
        vacancyDto.setCountry(vacancy.getCountry());
        vacancyDto.setCity(vacancy.getCity());
        vacancyDto.setExperience(vacancy.getExperience());
        vacancyDto.setAddress(vacancy.getAddress());
        vacancyDto.setEmploymentMode(vacancy.getEmploymentMode().getDescription());
        vacancyDto.setCompanyInfo(vacancy.getCompanyInfo());
        vacancyDto.setDescription(vacancy.getDescription());
        vacancyDto.setImageUrl(vacancy.getImageUrl());
        vacancyDto.setActive(vacancy.isActive());
        vacancyDto.setPhone(vacancy.getPhone());
        vacancyDto.setEmail(vacancy.getEmail());
        vacancyDto.setPublishDate(vacancy.getPublishDate());
        vacancyDto.setVacancyCategory(vacancy.getVacancyCategory().getCategoryName());

        return vacancyDto;
    }
}
