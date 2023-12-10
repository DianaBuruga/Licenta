package com.ulbs.careerstartup.entity;

import com.ulbs.careerstartup.constant.Constants;
import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "logo_url")
    private String logoUrl;

    private String address;

    @Column(nullable = false)
    @Pattern(regexp = Constants.URL_PATTERN, message = "Invalid URL format")
    private String url;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostedJob> postedJobs;
}
