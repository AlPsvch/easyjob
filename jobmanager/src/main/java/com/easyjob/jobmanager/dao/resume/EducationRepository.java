package com.easyjob.jobmanager.dao.resume;

import com.easyjob.jobmanager.entity.resume.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
}
