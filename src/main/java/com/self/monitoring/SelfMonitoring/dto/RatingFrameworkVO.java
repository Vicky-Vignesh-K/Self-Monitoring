package com.self.monitoring.SelfMonitoring.dto;

import org.springframework.stereotype.Component;

@Component
public class RatingFrameworkVO  {
    private Long frameworkRatingId;

    private RatingTableVO frameworkRatings;

    //private SelfMonitoringVo monitoring;

    private String ratingFrameworkStatus;

    private Integer ratingFrameworkOrder;

    private Double ratingFrameworkScore;

    public Long getFrameworkRatingId() {
        return frameworkRatingId;
    }

    public void setFrameworkRatingId(Long frameworkRatingId) {
        this.frameworkRatingId = frameworkRatingId;
    }

    public RatingTableVO getFrameworkRatings() {
        return frameworkRatings;
    }

    public void setFrameworkRatings(RatingTableVO frameworkRatings) {
        this.frameworkRatings = frameworkRatings;
    }

    public String getRatingFrameworkStatus() {
        return ratingFrameworkStatus;
    }

    public void setRatingFrameworkStatus(String ratingFrameworkStatus) {
        this.ratingFrameworkStatus = ratingFrameworkStatus;
    }

    public Integer getRatingFrameworkOrder() {
        return ratingFrameworkOrder;
    }

    public void setRatingFrameworkOrder(Integer ratingFrameworkOrder) {
        this.ratingFrameworkOrder = ratingFrameworkOrder;
    }

    public Double getRatingFrameworkScore() {
        return ratingFrameworkScore;
    }

    public void setRatingFrameworkScore(Double ratingFrameworkScore) {
        this.ratingFrameworkScore = ratingFrameworkScore;
    }

}
