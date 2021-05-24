package com.easyjob.jobmanager.dao.resume;

import com.easyjob.jobmanager.entity.JobCategory;
import com.easyjob.jobmanager.entity.resume.Gender;
import com.easyjob.jobmanager.entity.resume.Resume;
import com.easyjob.jobmanager.entity.resume.ResumePage;
import com.easyjob.jobmanager.entity.resume.ResumeSearchCriteria;
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
public class ResumeCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public ResumeCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Resume> findWithFilters(ResumePage resumePage, ResumeSearchCriteria resumeSearchCriteria) {
        CriteriaQuery<Resume> resumeCriteriaQuery = criteriaBuilder.createQuery(Resume.class);
        Root<Resume> resumeRoot = resumeCriteriaQuery.from(Resume.class);

        Predicate resumePredicate = getPredicate(resumeSearchCriteria, resumeRoot);
        resumeCriteriaQuery.where(resumePredicate);
        setOrder(resumePage, resumeCriteriaQuery, resumeRoot);

        TypedQuery<Resume> typedQuery = entityManager.createQuery(resumeCriteriaQuery);
        typedQuery.setFirstResult(resumePage.getPageNumber() * resumePage.getPageSize());
        typedQuery.setMaxResults(resumePage.getPageSize());

        Pageable pageable = getPageable(resumePage);

        long resumesCount = getResumesCount(resumePredicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, resumesCount);
    }

    private Predicate getPredicate(ResumeSearchCriteria resumeSearchCriteria, Root<Resume> resumeRoot) {
        List<Predicate> searchPredicates = new ArrayList<>();
        String searchParam = resumeSearchCriteria.getSearch();
        if (Objects.nonNull(searchParam)) {
            searchPredicates.add(criteriaBuilder.like(resumeRoot.get("name"), "%" + searchParam + "%"));
        }
        Gender genderParam = resumeSearchCriteria.getGender();
        if (Objects.nonNull(genderParam)) {
            searchPredicates.add(criteriaBuilder.equal(resumeRoot.get("gender"), genderParam));
        }
        Predicate searchPredicate = criteriaBuilder.and(searchPredicates.toArray(new Predicate[0]));

        List<Predicate> categoryPredicates = new ArrayList<>();
        List<JobCategory> jobCategories = resumeSearchCriteria.getJobCategories();
        Predicate categoryPredicate;
        if (Objects.nonNull(jobCategories) && !jobCategories.isEmpty()) {
            for (JobCategory jobCategory : jobCategories) {
                categoryPredicates.add(criteriaBuilder.equal(resumeRoot.get("resumeCategory"), jobCategory));
            }
            categoryPredicate = criteriaBuilder.or(categoryPredicates.toArray(new Predicate[0]));
        } else {
            categoryPredicate = criteriaBuilder.and(categoryPredicates.toArray(new Predicate[0]));
        }

        return criteriaBuilder.and(searchPredicate, categoryPredicate);
    }

    private void setOrder(ResumePage resumePage, CriteriaQuery<Resume> resumeCriteriaQuery, Root<Resume> resumeRoot) {
        if (resumePage.getSortDirection().equals(Sort.Direction.ASC)) {
            resumeCriteriaQuery.orderBy(criteriaBuilder.asc(resumeRoot.get(resumePage.getSortByParameter())));
        } else {
            resumeCriteriaQuery.orderBy(criteriaBuilder.desc(resumeRoot.get(resumePage.getSortByParameter())));
        }
    }

    private Pageable getPageable(ResumePage resumePage) {
        Sort sort = Sort.by(resumePage.getSortDirection(), resumePage.getSortByParameter());
        return PageRequest.of(resumePage.getPageNumber(), resumePage.getPageSize(), sort);
    }

    private long getResumesCount(Predicate resumePredicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Resume> countRoot = countQuery.from(Resume.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(resumePredicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
