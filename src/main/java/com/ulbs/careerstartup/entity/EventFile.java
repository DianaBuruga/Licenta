package com.ulbs.careerstartup.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "event_file")
public class EventFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "event_file_name", nullable = false)
    private String eventFileName;

    @Lob
    @Column(name = "event_file_content", nullable = false)
    private Byte[] eventFileContent;
}
