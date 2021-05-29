package com.easyjob.jobmanager.service.vacancy;

import com.easyjob.jobmanager.dao.vacancy.VacancyCriteriaRepository;
import com.easyjob.jobmanager.dao.vacancy.VacancyRepository;
import com.easyjob.jobmanager.entity.vacancy.Vacancy;
import com.easyjob.jobmanager.entity.vacancy.VacancyPage;
import com.easyjob.jobmanager.entity.vacancy.VacancySearchCriteria;
import com.easyjob.jobmanager.exception.VacancyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private final VacancyCriteriaRepository vacancyCriteriaRepository;

    @Autowired
    public VacancyService(VacancyRepository vacancyRepository, VacancyCriteriaRepository vacancyCriteriaRepository) {
        this.vacancyRepository = vacancyRepository;
        this.vacancyCriteriaRepository = vacancyCriteriaRepository;
    }

    public List<Vacancy> findAllVacancies() {
        return vacancyRepository.findAll();
    }

    public Page<Vacancy> findVacancies(VacancyPage vacancyPage) {
        Sort sortOptions = Sort.by(vacancyPage.getSortDirection(), vacancyPage.getSortByParameter());
        Pageable pageable = PageRequest.of(vacancyPage.getPageNumber(), vacancyPage.getPageSize(), sortOptions);

        return vacancyRepository.findAll(pageable);
    }

    public Page<Vacancy> findVacancies(VacancyPage vacancyPage, VacancySearchCriteria vacancySearchCriteria) {
        return vacancyCriteriaRepository.findWithFilters(vacancyPage, vacancySearchCriteria);
    }

    public Vacancy findVacancy(Long vacancyId) {
        return vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new VacancyNotFoundException("Vacancy with id: " + vacancyId + " was not found"));
    }

    public Vacancy addVacancy(Vacancy vacancy) {
        if(StringUtils.isEmpty(vacancy.getName())) {
            throw new IllegalArgumentException("Vacancy name should not be empty");
        }

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
