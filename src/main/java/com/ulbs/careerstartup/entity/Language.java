package com.ulbs.careerstartup.entity;

import com.ulbs.careerstartup.constant.LanguageLevel;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String language;

    @Column(nullable = false, length = 3)
    private String listening;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 2)
    private LanguageLevel reading;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 2)
    private LanguageLevel speaking;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 2)
    private LanguageLevel conversation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 2)
    private LanguageLevel writing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User user;
}
