package com.self.monitoring.SelfMonitoring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Rating_Framework")
public class RatingFramework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Rating_Framework_Id")
    private Long ratingFrameworkId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Rating_Id", referencedColumnName = "Rating_Id")
    private RatingTable frameworkRatings;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Framework_Id", referencedColumnName = "Framework_Id")
    private Framework framework;

    @Column(name = "Rating_Framework_Status")
    private String ratingFrameworkStatus;

    @Column(name = "Rating_Framework_Order")
    private Integer ratingFrameworkOrder;

    @Column(name = "Rating_Framework_Score")
    private Double ratingFrameworkScore;

    public Long getRatingFrameworkId() {
        return ratingFrameworkId;
    }

    public void setRatingFrameworkId(Long ratingFrameworkId) {
        this.ratingFrameworkId = ratingFrameworkId;
    }

    public RatingTable getFrameworkRatings() {
        return frameworkRatings;
    }

    public void setFrameworkRatings(RatingTable frameworkRatings) {
        this.frameworkRatings = frameworkRatings;
    }

    public Framework getFramework() {
        return framework;
    }

    public void setFramework(Framework framework) {
        this.framework = framework;
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
