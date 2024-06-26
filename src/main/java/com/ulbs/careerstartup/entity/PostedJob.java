package com.ulbs.careerstartup.entity;

import com.ulbs.careerstartup.constant.JobStatus;
import com.ulbs.careerstartup.constant.JobType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "posted_job", schema = "career_startup")
public class PostedJob {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(unique = true, nullable = false)
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private String position;

    @NotNull
    @Column(name = "open_until", nullable = false)
    private Timestamp openUntil;

    @NotNull
    @Column(name = "posted_date", nullable = false)
    private Timestamp postedDate;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @NotNull
    @Column(nullable = false, length = 100)
    private String location;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private JobType type;

    @ToString.Exclude
    @OneToMany(mappedBy = "postedJob")
    private Collection<JobCandidates> jobCandidates;

    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "posted_job_skills",
            joinColumns = @JoinColumn(name = "posted_job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Collection<Skill> skills;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private Company company;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostedJob job = (PostedJob) o;
        return Objects.equals(id, job.id) && Objects.equals(description, job.description) && Objects.equals(position, job.position) && Objects.equals(openUntil, job.openUntil) && Objects.equals(postedDate, job.postedDate) && status == job.status && Objects.equals(location, job.location) && type == job.type && Objects.equals(jobCandidates, job.jobCandidates) && Objects.equals(skills, job.skills) && Objects.equals(user, job.user) && Objects.equals(company, job.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, position, openUntil, postedDate, status, location, type, jobCandidates, skills, user, company);
    }
}
