package com.self.monitoring.SelfMonitoring.dto;

import com.self.monitoring.SelfMonitoring.entity.FrameworkAnswer;
import com.self.monitoring.SelfMonitoring.entity.Question;
import com.self.monitoring.SelfMonitoring.entity.RatingFramework;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import org.springframework.stereotype.Component;

@Component
public class QuestionRatingVO {

    private Long questionRatingId;

    private QuestionVO questionVO;

    private RatingFrameworkVO ratingFrameworkVO;

//    private FrameworkAnswerVO frameworkAnswerVO;


    public Long getQuestionRatingId() {
        return questionRatingId;
    }

    public void setQuestionRatingId(Long questionRatingId) {
        this.questionRatingId = questionRatingId;
    }
    public QuestionVO getQuestionVO() {
        return questionVO;
    }

    public void setQuestionVO(QuestionVO questionVO) {
        this.questionVO = questionVO;
    }

    public RatingFrameworkVO getRatingFrameworkVO() {
        return ratingFrameworkVO;
    }

    public void setRatingFrameworkVO(RatingFrameworkVO ratingFrameworkVO) {
        this.ratingFrameworkVO = ratingFrameworkVO;
    }
}
