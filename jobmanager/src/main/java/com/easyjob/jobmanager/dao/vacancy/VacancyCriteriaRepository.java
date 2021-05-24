package com.easyjob.jobmanager.dao.vacancy;

import com.easyjob.jobmanager.entity.JobCategory;
import com.easyjob.jobmanager.entity.vacancy.EmploymentMode;
import com.easyjob.jobmanager.entity.vacancy.Vacancy;
import com.easyjob.jobmanager.entity.vacancy.VacancyPage;
import com.easyjob.jobmanager.entity.vacancy.VacancySearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class VacancyCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public VacancyCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Vacancy> findWithFilters(VacancyPage vacancyPage, VacancySearchCriteria vacancySearchCriteria) {
        CriteriaQuery<Vacancy> vacancyCriteriaQuery = criteriaBuilder.createQuery(Vacancy.class);
        Root<Vacancy> vacancyRoot = vacancyCriteriaQuery.from(Vacancy.class);

        Predicate vacancyPredicate = getPredicate(vacancySearchCriteria, vacancyRoot);
        vacancyCriteriaQuery.where(vacancyPredicate);
        setOrder(vacancyPage, vacancyCriteriaQuery, vacancyRoot);

        TypedQuery<Vacancy> typedQuery = entityManager.createQuery(vacancyCriteriaQuery);
        typedQuery.setFirstResult(vacancyPage.getPageNumber() * vacancyPage.getPageSize());
        typedQuery.setMaxResults(vacancyPage.getPageSize());

        Pageable pageable = getPageable(vacancyPage);

        long vacanciesCount = getVacanciesCount(vacancyPredicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, vacanciesCount);
    }

    private Predicate getPredicate(VacancySearchCriteria vacancySearchCriteria, Root<Vacancy> vacancyRoot) {
        List<Predicate> searchPredicates = new ArrayList<>();
        String searchParam = vacancySearchCriteria.getSearch();

        Predicate searchPredicate;
        if (Objects.nonNull(searchParam)) {
            searchPredicates.add(criteriaBuilder.like(vacancyRoot.get("name"), "%" + searchParam + "%"));
            searchPredicates.add(criteriaBuilder.like(vacancyRoot.get("description"), "%" + searchParam + "%"));
            searchPredicate = criteriaBuilder.or(searchPredicates.toArray(new Predicate[0]));
        } else {
            searchPredicate = criteriaBuilder.and(searchPredicates.toArray(new Predicate[0]));
        }

        List<Predicate> categoryPredicates = new ArrayList<>();
        List<JobCategory> jobCategories = vacancySearchCriteria.getJobCategories();
        Predicate categoryPredicate;
        if (Objects.nonNull(jobCategories) && !jobCategories.isEmpty()) {
            for (JobCategory jobCategory : jobCategories) {
                categoryPredicates.add(criteriaBuilder.equal(vacancyRoot.get("vacancyCategory"), jobCategory));
            }
            categoryPredicate = criteriaBuilder.or(categoryPredicates.toArray(new Predicate[0]));
        } else {
            categoryPredicate = criteriaBuilder.and(categoryPredicates.toArray(new Predicate[0]));
        }

        List<Predicate> employmentPredicates = new ArrayList<>();
        List<EmploymentMode> employmentModes = vacancySearchCriteria.getEmploymentModes();
        Predicate employmentPredicate;
        if (Objects.nonNull(employmentModes) && !employmentModes.isEmpty()) {
            for (EmploymentMode employmentMode : employmentModes) {
                employmentPredicates.add(criteriaBuilder.equal(vacancyRoot.get("employmentMode"), employmentMode));
            }
            employmentPredicate = criteriaBuilder.or(employmentPredicates.toArray(new Predicate[0]));
        } else {
            employmentPredicate = criteriaBuilder.and(employmentPredicates.toArray(new Predicate[0]));
        }

        return criteriaBuilder.and(searchPredicate, categoryPredicate, employmentPredicate);
    }

    private void setOrder(VacancyPage vacancyPage, CriteriaQuery<Vacancy> vacancyCriteriaQuery, Root<Vacancy> vacancyRoot) {
        if (vacancyPage.getSortDirection().equals(Sort.Direction.ASC)) {
            vacancyCriteriaQuery.orderBy(criteriaBuilder.asc(vacancyRoot.get(vacancyPage.getSortByParameter())));
        } else {
            vacancyCriteriaQuery.orderBy(criteriaBuilder.desc(vacancyRoot.get(vacancyPage.getSortByParameter())));
        }
    }

    private Pageable getPageable(VacancyPage vacancyPage) {
        Sort sort = Sort.by(vacancyPage.getSortDirection(), vacancyPage.getSortByParameter());
        return PageRequest.of(vacancyPage.getPageNumber(), vacancyPage.getPageSize(), sort);
    }

    private long getVacanciesCount(Predicate vacancyPredicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Vacancy> countRoot = countQuery.from(Vacancy.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(vacancyPredicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
