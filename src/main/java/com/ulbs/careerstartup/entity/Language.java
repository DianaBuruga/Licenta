package com.ulbs.careerstartup.entity;

import com.ulbs.careerstartup.constant.LanguageLevel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Language {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(unique = true, nullable = false)
    private UUID id;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LanguageLevel listening;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LanguageLevel reading;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LanguageLevel speaking;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LanguageLevel conversation;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LanguageLevel writing;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language that = (Language) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(listening, that.listening) && Objects.equals(reading, that.reading) && Objects.equals(speaking, that.speaking) && Objects.equals(conversation, that.conversation) && Objects.equals(writing, that.writing) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, listening, reading, speaking, conversation, writing, user);
    }
}
