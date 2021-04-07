package com.easyjob.jobmanager.controller.vacancy;

import com.easyjob.jobmanager.entity.vacancy.Vacancy;
import com.easyjob.jobmanager.service.vacancy.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/vacancy")
public class VacancyController {

    private final VacancyService vacancyService;

    @Autowired
    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Vacancy>> getAllVacancies() {
        List<Vacancy> vacancies = vacancyService.findAllVacancies();
        return new ResponseEntity<>(vacancies, HttpStatus.OK);
    }

    @GetMapping(path = "/find/{id}")
    public ResponseEntity<Vacancy> getVacancy(@PathVariable("id") Long id) {
        Vacancy vacancy = vacancyService.findVacancy(id);
        return new ResponseEntity<>(vacancy, HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Vacancy> addVacancy(@RequestBody Vacancy vacancy) {
        Vacancy addedVacancy = vacancyService.addVacancy(vacancy);
        return new ResponseEntity<>(addedVacancy, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Vacancy> updateVacancy(@RequestBody Vacancy vacancy) {
        Vacancy updatedVacancy = vacancyService.updateVacancy(vacancy);
        return new ResponseEntity<>(updatedVacancy, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteVacancy(@PathVariable("id") Long id) {
        vacancyService.deleteVacancy(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
