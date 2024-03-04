package com.ulbs.careerstartup.repository;

import com.ulbs.careerstartup.entity.UserSkills;
import com.ulbs.careerstartup.entity.pk.UserSkillPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSkillsRepository extends JpaRepository<UserSkills, UserSkillPK>, JpaSpecificationExecutor<UserSkills> {
}