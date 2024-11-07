package com.self.monitoring.SelfMonitoring.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionVO {
    private Long questionId;

    private String questionName;

    private Integer questionOrder;

    private String questionStatus;

    private List<RatingFrameworkVO> ratingFrameworkVOList;

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

    public List<RatingFrameworkVO> getRatingFrameworkVOList() {
        return ratingFrameworkVOList;
    }

    public void setRatingFrameworkVOList(List<RatingFrameworkVO> ratingFrameworkVOList) {
        this.ratingFrameworkVOList = ratingFrameworkVOList;
    }

}
