package com.self.monitoring.SelfMonitoring.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "framework_answer")
public class FrameworkAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "framework_answer_id")
    private Long frameworkAnswerId;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "frameworkAnswer",fetch = FetchType.LAZY)
    private List<QuestionRating> questionRatingList;

    @Column(name = "framework_answer_status")
    private String frameworkAnswerStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    private Framework framework;

    @Column(name = "user_entered_date")
    private Date userEnteredDate;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "system_date")
    private Date systemDate;

    public Long getFrameworkAnswerId() {
        return frameworkAnswerId;
    }

    public void setFrameworkAnswerId(Long frameworkAnswerId) {
        this.frameworkAnswerId = frameworkAnswerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<QuestionRating> getQuestionRatingList() {
        return questionRatingList;
    }

    public void setQuestionRatingList(List<QuestionRating> questionRatingList) {
        this.questionRatingList = questionRatingList;
    }

    public String getFrameworkAnswerStatus() {
        return frameworkAnswerStatus;
    }

    public void setFrameworkAnswerStatus(String frameworkAnswerStatus) {
        this.frameworkAnswerStatus = frameworkAnswerStatus;
    }

    public Framework getFramework() {
        return framework;
    }

    public void setFramework(Framework framework) {
        this.framework = framework;
    }


//    public List<Framework> getFramework() {
//        return framework;
//    }
//
//    public void setFramework(List<Framework> framework) {
//        this.framework = framework;
//    }

    public Date getUserEnteredDate() {
        return userEnteredDate;
    }

    public void setUserEnteredDate(Date userEnteredDate) {
        this.userEnteredDate = userEnteredDate;
    }

    public Date getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(Date systemDate) {
        this.systemDate = systemDate;
    }
}
