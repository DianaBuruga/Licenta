package com.ulbs.careerstartup.entity;

import com.ulbs.careerstartup.constant.Constants;
import com.ulbs.careerstartup.constant.Role;
import com.ulbs.careerstartup.constant.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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
public class User {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(unique = true, nullable = false)
    private UUID id;

    @NotNull
    @Email(message = "Invalid email")
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @Column(nullable = false)
    private String name;

    @Pattern(regexp = Constants.PHONE_REGEX, message = "Invalid phone number")
    private String phone;

    @URL
    private String website;

    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy = "writer",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    @ToString.Exclude
    private Collection<Bibliography> bibliographies;

    @OneToMany(mappedBy = "creator",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    @ToString.Exclude
    private Collection<Event> events;

    @ManyToMany(mappedBy = "subscribers",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @ToString.Exclude
    private Collection<Event> eventSubscribers;

    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    @ToString.Exclude
    private Collection<Experience> experiences;

    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    @ToString.Exclude
    private Collection<JobCandidates> jobCandidates;

    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    @ToString.Exclude
    private Collection<JobHistory> jobHistories;

    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    @ToString.Exclude
    private Collection<Language> languages;

    @OneToMany(mappedBy = "sender",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    @ToString.Exclude
    private Collection<Message> sentMessages;

    @OneToMany(mappedBy = "receiver",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    @ToString.Exclude
    private Collection<Message> receivedMessages;

    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    @ToString.Exclude
    private Collection<Notification> notifications;

    @OneToMany(mappedBy = "teacher",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    @ToString.Exclude
    private Collection<Referral> writtenReferrals;

    @OneToMany(mappedBy = "student",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    @ToString.Exclude
    private Collection<Referral> receivedReferrals;

    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    @ToString.Exclude
    private Collection<Specialization> specializations;

    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    @ToString.Exclude
    private Collection<UserSkills> skills;

    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    @ToString.Exclude
    private Collection<PostedJob> postedJobs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(role, that.role) && Objects.equals(name, that.name) && Objects.equals(phone, that.phone) && Objects.equals(website, that.website) && Objects.equals(description, that.description) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, role, name, phone, website, description, status);
    }
}
