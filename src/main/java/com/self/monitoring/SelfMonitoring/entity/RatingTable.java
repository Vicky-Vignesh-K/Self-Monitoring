package com.self.monitoring.SelfMonitoring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Rating_Table")
public class RatingTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Rating_Id")
    private Long ratingId;

    @Column(name = "Rating_Name")
    private String ratingName;

    @Column(name = "Rating_Key")
    private String ratingKey;

    @Column(name = "Colour_Code")
    private String colour;

    @Column(name = "Rating_Order")
    private Integer ratingOrder;

    @Column(name = "Rating_Status")
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
