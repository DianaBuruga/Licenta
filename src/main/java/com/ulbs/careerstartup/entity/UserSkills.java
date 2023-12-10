package com.ulbs.careerstartup.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "user_skills")
@Data
public class UserSkills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "id", nullable = false)
    private Skill skill;

    @Column(name = "proficiency", nullable = false)
    @Min(value = 1, message = "Proficiency must be at least 1")
    @Max(value = 100, message = "Proficiency must be at most 100")
    private Integer proficiency;
}
