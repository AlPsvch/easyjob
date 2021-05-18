package com.easyjob.jobmanager.service.vacancy;

import com.easyjob.jobmanager.dao.vacancy.VacancyRepository;
import com.easyjob.jobmanager.entity.vacancy.Vacancy;
import com.easyjob.jobmanager.entity.vacancy.VacancyPage;
import com.easyjob.jobmanager.exception.VacancyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VacancyService {

    private final VacancyRepository vacancyRepository;

    @Autowired
    public VacancyService(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    public List<Vacancy> findAllVacancies() {
        return vacancyRepository.findAll();
    }

    public Page<Vacancy> findVacancies(VacancyPage vacancyPage) {
        Sort sortOptions = Sort.by(vacancyPage.getSortDirection(), vacancyPage.getSortByParameter());
        Pageable pageable = PageRequest.of(vacancyPage.getPageNumber(), vacancyPage.getPageSize(), sortOptions);

        return vacancyRepository.findAll(pageable);
    }

    public Vacancy findVacancy(Long vacancyId) {
        return vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new VacancyNotFoundException("Vacancy with id: " + vacancyId + " was not found"));
    }

    public Vacancy addVacancy(Vacancy vacancy) {
        vacancy.setPublishDate(LocalDateTime.now());
        return vacancyRepository.save(vacancy);
    }

    public Vacancy updateVacancy(Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }

    public void deleteVacancy(Long vacancyId) {
        vacancyRepository.deleteById(vacancyId);
    }
}
