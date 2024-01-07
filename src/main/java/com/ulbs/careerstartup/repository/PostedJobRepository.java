package com.ulbs.careerstartup.repository;

import com.ulbs.careerstartup.entity.PostedJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostedJobRepository extends JpaRepository<PostedJob, UUID> {
}