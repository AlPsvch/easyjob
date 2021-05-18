package com.easyjob.jobmanager.dao.resume;

import com.easyjob.jobmanager.entity.resume.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

}
