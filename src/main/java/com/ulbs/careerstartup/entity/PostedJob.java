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
    private Collection<JobCandidates> jobCandidatesById;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private Company company;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostedJob postedJob = (PostedJob) o;
        return Objects.equals(id, postedJob.id) && Objects.equals(description, postedJob.description) && Objects.equals(openUntil, postedJob.openUntil) && Objects.equals(postedDate, postedJob.postedDate) && status == postedJob.status && Objects.equals(location, postedJob.location) && type == postedJob.type && Objects.equals(jobCandidatesById, postedJob.jobCandidatesById) && Objects.equals(company, postedJob.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, openUntil, postedDate, status, location, type, jobCandidatesById, company);
    }
}
