package com.easyjob.jobmanager.service.resume;

import com.easyjob.jobmanager.dao.resume.ContactInfoRepository;
import com.easyjob.jobmanager.dao.resume.EducationRepository;
import com.easyjob.jobmanager.dao.resume.ResumeCriteriaRepository;
import com.easyjob.jobmanager.dao.resume.ResumeRepository;
import com.easyjob.jobmanager.entity.resume.*;
import com.easyjob.jobmanager.exception.ResumeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final ContactInfoRepository contactInfoRepository;
    private final EducationRepository educationRepository;
    private final ResumeCriteriaRepository resumeCriteriaRepository;

    @Autowired
    public ResumeService(ResumeRepository resumeRepository, ContactInfoRepository contactInfoRepository, EducationRepository educationRepository, ResumeCriteriaRepository resumeCriteriaRepository) {
        this.resumeRepository = resumeRepository;
        this.contactInfoRepository = contactInfoRepository;
        this.educationRepository = educationRepository;
        this.resumeCriteriaRepository = resumeCriteriaRepository;
    }

    public List<Resume> findAllResumes() {
        return resumeRepository.findAll();
    }

    public Page<Resume> findResumes(ResumePage resumePage) {
        Sort sortOptions = Sort.by(resumePage.getSortDirection(), resumePage.getSortByParameter());
        Pageable pageable = PageRequest.of(resumePage.getPageNumber(), resumePage.getPageSize(), sortOptions);

        return resumeRepository.findAll(pageable);
    }

    public Page<Resume> findResumes(ResumePage resumePage, ResumeSearchCriteria resumeSearchCriteria) {
        return resumeCriteriaRepository.findWithFilters(resumePage, resumeSearchCriteria);
    }

    public Resume findResume(Long resumeId) {
        return resumeRepository.findById(resumeId)
                .orElseThrow(() -> new ResumeNotFoundException("Resume with id: " + resumeId + " was not found"));
    }

    public Resume addResume(Resume resume) {
        ContactInfo contactInfo = contactInfoRepository.save(resume.getContactInfo());
        Education education = educationRepository.save(resume.getEducation());

        resume.setContactInfo(contactInfo);
        resume.setEducation(education);
        resume.setPublishDate(LocalDateTime.now());

        return resumeRepository.save(resume);
    }

    public Resume updateResume(Resume resume) {
        ContactInfo contactInfo = contactInfoRepository.save(resume.getContactInfo());
        Education education = educationRepository.save(resume.getEducation());

        resume.setContactInfo(contactInfo);
        resume.setEducation(education);

        return resumeRepository.save(resume);
    }

    public void deleteResume(Long resumeId) {
        resumeRepository.deleteById(resumeId);
    }
}
