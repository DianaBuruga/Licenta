package com.ulbs.careerstartup.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "interview_file")
@Data
public class InterviewFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false)
    private byte[] content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_id")
    private Interview interview;
}
