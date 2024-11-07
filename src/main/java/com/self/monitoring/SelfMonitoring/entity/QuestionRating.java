package com.self.monitoring.SelfMonitoring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "question_rating")
public class QuestionRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_rating_id")
    private Long questionRatingId;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Question question;

    @ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private RatingFramework ratingFramework;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "framework_answer_id", referencedColumnName = "framework_answer_id")
    private FrameworkAnswer frameworkAnswer;

    public Long getQuestionRatingId() {
        return questionRatingId;
    }

    public void setQuestionRatingId(Long questionRatingId) {
        this.questionRatingId = questionRatingId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public RatingFramework getRatingFramework() {
        return ratingFramework;
    }

    public void setRatingFramework(RatingFramework ratingFramework) {
        this.ratingFramework = ratingFramework;
    }

    public FrameworkAnswer getFrameworkAnswer() {
        return frameworkAnswer;
    }

    public void setFrameworkAnswer(FrameworkAnswer frameworkAnswer) {
        this.frameworkAnswer = frameworkAnswer;
    }
}
