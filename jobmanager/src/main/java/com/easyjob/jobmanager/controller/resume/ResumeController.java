package com.easyjob.jobmanager.controller.resume;

import com.easyjob.jobmanager.entity.resume.Resume;
import com.easyjob.jobmanager.service.resume.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/resume")
public class ResumeController {

    private final ResumeService resumeService;

    @Autowired
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Resume>> getAllResumes() {
        List<Resume> resumes = resumeService.findAllResumes();
        return new ResponseEntity<>(resumes, HttpStatus.OK);
    }

    @GetMapping(path = "/find/{id}")
    public ResponseEntity<Resume> getResume(@PathVariable("id") Long id) {
        Resume resume = resumeService.findResume(id);
        return new ResponseEntity<>(resume, HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Resume> addResume(@RequestBody Resume resume) {
        Resume addedResume = resumeService.addResume(resume);
        return new ResponseEntity<>(addedResume, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Resume> updateResume(@RequestBody Resume resume) {
        Resume updatedResume = resumeService.updateResume(resume);
        return new ResponseEntity<>(updatedResume, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteResume(@PathVariable("id") Long id) {
        resumeService.deleteResume(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
