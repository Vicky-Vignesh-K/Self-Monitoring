package com.self.monitoring.SelfMonitoring.dto;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class FrameworkVO {
    private Long frameworkId;

    private String frameworkStatus;

    private String frameworkDescription;

    private String frameworkName;

    private List<QuestionVO> frameworkQuestions;

    private List<RatingFrameworkVO> frameworkRatings;

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

    public List<QuestionVO> getFrameworkQuestions() {
        if(CollectionUtils.isEmpty(frameworkQuestions))
            frameworkQuestions = new ArrayList<>();
        return frameworkQuestions;
    }

    public void setFrameworkQuestions(List<QuestionVO> frameworkQuestions) {
        this.frameworkQuestions = frameworkQuestions;
    }

    public List<RatingFrameworkVO> getFrameworkRatings() {
        if(CollectionUtils.isEmpty(frameworkRatings)){
            frameworkRatings = new ArrayList<>();
        }
        return frameworkRatings;
    }

    public void setFrameworkRatings(List<RatingFrameworkVO> frameworkRatings) {
        this.frameworkRatings = frameworkRatings;
    }
}
