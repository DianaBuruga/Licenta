package com.ulbs.careerstartup.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import jakarta.validation.constraints.NotNull;
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
public class Skill {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(unique = true, nullable = false)
    private UUID id;

    @NotNull
    @Column(nullable = false, length = 100)
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "skill")
    private Collection<Bibliography> bibliographies;

    @ToString.Exclude
    @OneToMany(mappedBy = "skill")
    private Collection<UserSkills> userSkills;

    @ManyToMany(mappedBy = "skills")
    @ToString.Exclude
    private Collection<Course> courses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill that = (Skill) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
