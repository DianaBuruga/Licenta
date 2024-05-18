package com.ulbs.careerstartup.entity;

import com.ulbs.careerstartup.entity.pk.UserSkillPK;
import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.NotNull;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_skills")
public class UserSkills {

    @EmbeddedId
    private UserSkillPK id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("userId")
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @MapsId("skillId")
    @NotNull
    @JoinColumn(name = "skill_id", referencedColumnName = "id", nullable = false)
    private Skill skill;

    @Column(nullable = false)
    @NotNull
    private Integer proficiency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSkills that = (UserSkills) o;
        return Objects.equals(id, that.id) && Objects.equals(proficiency, that.proficiency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, proficiency);
    }
}
