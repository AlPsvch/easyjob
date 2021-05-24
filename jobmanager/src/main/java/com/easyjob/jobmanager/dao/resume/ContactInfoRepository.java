package com.easyjob.jobmanager.dao.resume;

import com.easyjob.jobmanager.entity.resume.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {
}
