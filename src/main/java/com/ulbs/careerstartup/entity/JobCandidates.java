package com.ulbs.careerstartup.entity;

import com.ulbs.careerstartup.entity.pk.JobCandidatesPK;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "job_candidates", schema = "career_startup")
public class JobCandidates {

    @EmbeddedId
    private JobCandidatesPK id;

    @Column(name = "application_date")
    private Timestamp applicationDate;

    @ToString.Exclude
    @MapsId("candidateId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "candidate_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ToString.Exclude
    @MapsId("jobId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "job_id", referencedColumnName = "id", nullable = false)
    private PostedJob postedJob;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobCandidates that = (JobCandidates) o;
        return Objects.equals(id, that.id) && Objects.equals(applicationDate, that.applicationDate) && Objects.equals(user, that.user) && Objects.equals(postedJob, that.postedJob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, applicationDate, user, postedJob);
    }
}
