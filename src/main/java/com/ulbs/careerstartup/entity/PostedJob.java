package com.ulbs.careerstartup.entity;


import com.ulbs.careerstartup.constant.JobStatus;
import com.ulbs.careerstartup.constant.JobType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "posted_job")
@Data
public class PostedJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(name = "open_until")
    private LocalDateTime openUntil;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 60)
    private JobStatus status;

    @Column(nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobType type;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
