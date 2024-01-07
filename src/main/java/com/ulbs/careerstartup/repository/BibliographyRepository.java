package com.ulbs.careerstartup.repository;

import com.ulbs.careerstartup.entity.Bibliography;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface BibliographyRepository extends JpaRepository<Bibliography, UUID> {
    Collection<Bibliography> findBySkillIdIn(Collection<UUID> skillIds);
}