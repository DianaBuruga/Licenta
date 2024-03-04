package com.ulbs.careerstartup.repository;

import com.ulbs.careerstartup.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LanguageRepository extends JpaRepository<Language, UUID>, JpaSpecificationExecutor<Language> {
}