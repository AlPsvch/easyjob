package com.easyjob.jobmanager.controller.resume;

import com.easyjob.jobmanager.dto.resume.ContactInfoDto;
import com.easyjob.jobmanager.dto.resume.EducationDto;
import com.easyjob.jobmanager.dto.resume.ResumeDto;
import com.easyjob.jobmanager.entity.resume.ContactInfo;
import com.easyjob.jobmanager.entity.resume.Education;
import com.easyjob.jobmanager.entity.resume.Resume;
import com.easyjob.jobmanager.entity.resume.ResumePage;
import com.easyjob.jobmanager.service.resume.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/resume")
public class ResumeController {

    private final ResumeService resumeService;

    @Autowired
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<ResumeDto>> getAllResumes() {
        List<Resume> resumes = resumeService.findAllResumes();
        List<ResumeDto> resumesDto = resumes.stream().map(this::convertResumeToDto).collect(Collectors.toList());
        return new ResponseEntity<>(resumesDto, HttpStatus.OK);
    }

    @GetMapping(path = "/find")
    public ResponseEntity<Page<Resume>> getResumes(ResumePage resumePage) {
        Page<Resume> resumes = resumeService.findResumes(resumePage);
        return new ResponseEntity<>(resumes, HttpStatus.OK);
    }

    @GetMapping(path = "/find/{id}")
    public ResponseEntity<ResumeDto> getResume(@PathVariable("id") Long id) {
        Resume resume = resumeService.findResume(id);
        ResumeDto resumeDto = convertResumeToDto(resume);
        return new ResponseEntity<>(resumeDto, HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ResumeDto> addResume(@RequestBody Resume resume) {
        Resume addedResume = resumeService.addResume(resume);
        ResumeDto addedResumeDto = convertResumeToDto(addedResume);
        return new ResponseEntity<>(addedResumeDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ResumeDto> updateResume(@RequestBody Resume resume) {
        Resume updatedResume = resumeService.updateResume(resume);
        ResumeDto updatedResumeDto = convertResumeToDto(updatedResume);
        return new ResponseEntity<>(updatedResumeDto, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteResume(@PathVariable("id") Long id) {
        resumeService.deleteResume(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResumeDto convertResumeToDto(Resume resume) {
        ResumeDto resumeDto = new ResumeDto();

        resumeDto.setId(resume.getId());
        resumeDto.setName(resume.getName());
        resumeDto.setExperience(resume.getExperience());
        resumeDto.setContactInfo(convertContactInfoToDto(resume.getContactInfo()));
        resumeDto.setEducation(convertEducationToDto(resume.getEducation()));
        resumeDto.setGender(resume.getGender().getName());
        resumeDto.setActive(resume.isActive());
        resumeDto.setPublishDate(resume.getPublishDate());
        resumeDto.setResumeCategory(resume.getResumeCategory().getCategoryName());

        return resumeDto;
    }

    private ContactInfoDto convertContactInfoToDto(ContactInfo contactInfo) {
        ContactInfoDto contactInfoDto = new ContactInfoDto();

        contactInfoDto.setId(contactInfo.getId());
        contactInfoDto.setCountry(contactInfo.getCountry());
        contactInfoDto.setCity(contactInfo.getCity());
        contactInfoDto.setAddress(contactInfo.getAddress());
        contactInfoDto.setSkype(contactInfo.getSkype());
        contactInfoDto.setPhone(contactInfo.getPhone());

        return contactInfoDto;
    }

    private EducationDto convertEducationToDto(Education education) {
        EducationDto educationDto = new EducationDto();

        educationDto.setId(education.getId());
        educationDto.setUniversity(education.getUniversity());
        educationDto.setFaculty(education.getFaculty());
        educationDto.setQualification(education.getQualification());
        educationDto.setEntranceYear(education.getEntranceYear());
        educationDto.setGraduationYear(education.getGraduationYear());

        return educationDto;
    }
}
