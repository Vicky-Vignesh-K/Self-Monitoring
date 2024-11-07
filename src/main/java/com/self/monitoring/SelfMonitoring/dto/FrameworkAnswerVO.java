package com.self.monitoring.SelfMonitoring.dto;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class FrameworkAnswerVO {

    private Long frameworkAnswerId;

    private UserVO userVO;

    private List<QuestionRatingVO> questionRatingVOList;

    private String frameworkAnswerStatus;

    private FrameworkVO frameworkVO;

    private Date userEnteredDate;

    private Date systemDate;

    public Long getFrameworkAnswerId() {
        return frameworkAnswerId;
    }

    public void setFrameworkAnswerId(Long frameworkAnswerId) {
        this.frameworkAnswerId = frameworkAnswerId;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public List<QuestionRatingVO> getQuestionRatingVOList() {
        return questionRatingVOList;
    }

    public void setQuestionRatingVOList(List<QuestionRatingVO> questionRatingVOList) {
        this.questionRatingVOList = questionRatingVOList;
    }

    public String getFrameworkAnswerStatus() {
        return frameworkAnswerStatus;
    }

    public void setFrameworkAnswerStatus(String frameworkAnswerStatus) {
        this.frameworkAnswerStatus = frameworkAnswerStatus;
    }

    public FrameworkVO getFrameworkVO() {
        return frameworkVO;
    }

    public void setFrameworkVO(FrameworkVO frameworkVO) {
        this.frameworkVO = frameworkVO;
    }


//    public List<FrameworkVO> getFrameworkVO() {
//        return frameworkVO;
//    }
//
//    public void setFrameworkVO(List<FrameworkVO> frameworkVO) {
//        this.frameworkVO = frameworkVO;
//    }

    public Date getUserEnteredDate() {
        return userEnteredDate;
    }

    public void setUserEnteredDate(Date userEnteredDate) {
        this.userEnteredDate = userEnteredDate;
    }

    public Date getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(Date systemDate) {
        this.systemDate = systemDate;
    }
}
