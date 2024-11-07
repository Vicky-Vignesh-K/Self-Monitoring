package com.self.monitoring.SelfMonitoring.entity;

import jakarta.persistence.*;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Framework")
public class Framework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Framework_Id")
    private Long frameworkId;

    @Column(name = "Framework_Status")
    private String frameworkStatus;

    @Column(name = "Framework_Description",length = 1000)
    private String frameworkDescription;

    @Column(name = "Framework_Name")
    private String frameworkName;

    @OneToMany(cascade = CascadeType.ALL,mappedBy="framework",fetch = FetchType.LAZY)
    private Set<Question> frameworkQuestions;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "framework",fetch = FetchType.LAZY)
    private List<RatingFramework> frameworkRatings;

    public Long getFrameworkId() {
        return frameworkId;
    }

    public void setFrameworkId(Long frameworkId) {
        this.frameworkId = frameworkId;
    }

    public String getFrameworkStatus() {
        return frameworkStatus;
    }

    public void setFrameworkStatus(String frameworkStatus) {
        this.frameworkStatus = frameworkStatus;
    }

    public String getFrameworkDescription() {
        return frameworkDescription;
    }

    public void setFrameworkDescription(String frameworkDescription) {
        this.frameworkDescription = frameworkDescription;
    }

    public String getFrameworkName() {
        return frameworkName;
    }

    public void setFrameworkName(String frameworkName) {
        this.frameworkName = frameworkName;
    }

    public Set<Question> getFrameworkQuestions() {
        if(CollectionUtils.isEmpty(frameworkQuestions))
            frameworkQuestions = new HashSet<>();
        return frameworkQuestions;
    }

    public void setFrameworkQuestions(Set<Question> frameworkQuestions) {
        this.frameworkQuestions = frameworkQuestions;
    }

    public List<RatingFramework> getFrameworkRatings() {
        return frameworkRatings;
    }

    public void setFrameworkRatings(List<RatingFramework> frameworkRatings) {
        this.frameworkRatings = frameworkRatings;
    }
}
