package com.self.monitoring.SelfMonitoring.dto;

import org.springframework.stereotype.Component;

@Component
public class ScoreVO {

    private String userName;
    private Long userId;
    private Double marksScored;
    private Double Percentage;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getMarksScored() {
        return marksScored;
    }

    public void setMarksScored(Double marksScored) {
        this.marksScored = marksScored;
    }

    public Double getPercentage() {
        return Percentage;
    }

    public void setPercentage(Double percentage) {
        Percentage = percentage;
    }
}
