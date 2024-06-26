package com.ulbs.careerstartup.repository;

import com.ulbs.careerstartup.entity.File;
import com.ulbs.careerstartup.entity.pk.FilePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, FilePK>, JpaSpecificationExecutor<File> {
    Optional<File> findByName(String name);
}