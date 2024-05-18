package com.ulbs.careerstartup.entity;

import com.ulbs.careerstartup.constant.ReviewType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Review {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(unique = true, nullable = false)
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private String position;

    @NotNull
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewType type;

    @NotNull
    @Column(nullable = false)
    private Integer rating;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private Company company;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id) && Objects.equals(position, review.position) && Objects.equals(description, review.description) && type == review.type && Objects.equals(rating, review.rating) && Objects.equals(company, review.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, position, description, type, rating, company);
    }
}
