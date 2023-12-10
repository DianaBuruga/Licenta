package com.ulbs.careerstartup.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "language_file")
@Data
public class LanguageFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "language_id", nullable = false)
    private Long languageId;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Lob
    @Column(name = "file_content", nullable = false)
    private Byte[] fileContent;
}