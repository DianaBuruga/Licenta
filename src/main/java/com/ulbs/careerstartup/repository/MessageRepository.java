package com.ulbs.careerstartup.repository;

import com.ulbs.careerstartup.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> , JpaSpecificationExecutor<Message> {
}