package com.ulbs.careerstartup.entity;

import com.ulbs.careerstartup.constant.Constants;
import com.ulbs.careerstartup.constant.ExperienceType;
import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Data
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email", referencedColumnName = "email", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime date;

    @Pattern(regexp = Constants.URL_PATTERN, message = "Invalid URL format")
    private String url;

    @Column(nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private ExperienceType type;
}
