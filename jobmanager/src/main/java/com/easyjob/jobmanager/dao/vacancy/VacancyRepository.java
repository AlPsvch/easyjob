package com.easyjob.jobmanager.dao.vacancy;

import com.easyjob.jobmanager.entity.vacancy.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

}
