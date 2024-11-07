package com.self.monitoring.SelfMonitoring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Question_Table")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Question_Id")
    private Long questionId;

    @Column(name = "Question_Name")
    private String questionName;

    @Column(name = "Question_Order")
    private Integer questionOrder;

    @Column(name = "Question_Status")
    private String questionStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Framework_Id",referencedColumnName = "Framework_Id")
    private Framework framework;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Integer getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(Integer questionOrder) {
        this.questionOrder = questionOrder;
    }

    public String getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(String questionStatus) {
        this.questionStatus = questionStatus;
    }

    public Framework getFrameworkId() {
        return framework;
    }

    public void setFramework(Framework framework) {
        this.framework = framework;
    }
}
