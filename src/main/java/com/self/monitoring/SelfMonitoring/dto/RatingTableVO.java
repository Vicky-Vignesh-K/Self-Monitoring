package com.self.monitoring.SelfMonitoring.dto;


import org.springframework.stereotype.Component;

@Component
public class RatingTableVO {
    private Long ratingId;

    private String ratingName;

    private String ratingKey;

    private String colour;

    private Integer ratingOrder;

    private String status;

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public String getRatingName() {
        return ratingName;
    }

    public void setRatingName(String ratingName) {
        this.ratingName = ratingName;
    }

    public String getRatingKey() {
        return ratingKey;
    }

    public void setRatingKey(String ratingKey) {
        this.ratingKey = ratingKey;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Integer getRatingOrder() {
        return ratingOrder;
    }

    public void setRatingOrder(Integer ratingOrder) {
        this.ratingOrder = ratingOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
