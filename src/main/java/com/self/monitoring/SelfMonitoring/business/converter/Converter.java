package com.self.monitoring.SelfMonitoring.business.converter;

import com.self.monitoring.SelfMonitoring.configuration.NoDataException;
import com.self.monitoring.SelfMonitoring.configuration.Ratings;
import com.self.monitoring.SelfMonitoring.configuration.Status;
import com.self.monitoring.SelfMonitoring.dto.FrameworkVO;
import com.self.monitoring.SelfMonitoring.dto.QuestionVO;
import com.self.monitoring.SelfMonitoring.dto.RatingFrameworkVO;
import com.self.monitoring.SelfMonitoring.dto.RatingTableVO;
import com.self.monitoring.SelfMonitoring.entity.Framework;
import com.self.monitoring.SelfMonitoring.entity.Question;
import com.self.monitoring.SelfMonitoring.entity.RatingFramework;
import com.self.monitoring.SelfMonitoring.entity.RatingTable;
import com.self.monitoring.SelfMonitoring.repository.IRatingTableRepo;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Converter {

    @Autowired
    private IRatingTableRepo ratingTableRepo;

    public void questionConverter(List<QuestionVO> questionVOList, Framework framework) {
        AtomicInteger num = new AtomicInteger(1);
        questionVOList.forEach(questionVO -> {
            try {
                Question question = new Question();
                if (!StringUtils.isBlank(questionVO.getQuestionName()))
                    question.setQuestionName(questionVO.getQuestionName());
                else {throw new NoDataException("Question Name is Null of Empty");}
                if (questionVO.getQuestionStatus() == null)
                    question.setQuestionStatus(String.valueOf(Status.ACTIVE));
                question.setQuestionOrder(num.getAndIncrement());
                question.setFramework(framework);
                framework.getFrameworkQuestions().add(question);
            } catch (NoDataException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<RatingFramework> ratingConverter(List<RatingFrameworkVO> ratingFrameworkVOList, Framework framework) throws NoDataException {
        AtomicInteger num = new AtomicInteger(1);
        List<RatingFramework> frameworkList = new ArrayList<>();
        ratingFrameworkVOList.forEach(ratingFrameworkVO -> {
            try {
                RatingFramework ratingFramework = new RatingFramework();
                if (ratingFrameworkVO.getRatingFrameworkStatus() == null)
                    ratingFramework.setRatingFrameworkStatus(String.valueOf(Status.ACTIVE));
                ratingFramework.setRatingFrameworkOrder(num.getAndIncrement());
                if (!StringUtils.isBlank(ratingFrameworkVO.getFrameworkRatings().getRatingName()))
                    ratingFramework.setFrameworkRatings(getRating(ratingFrameworkVO.getFrameworkRatings().getRatingName()));
                else {
                    throw new NoDataException("Rating Framework Name is Null or Empty");
                }
                if (ratingFrameworkVO.getRatingFrameworkScore() != null && ratingFrameworkVO.getRatingFrameworkScore() > 0) {
                    ratingFramework.setRatingFrameworkScore(ratingFrameworkVO.getRatingFrameworkScore());
                } else {
                    throw new NoDataException("Framework Score is null");
                }
                ratingFramework.setFramework(framework);
                frameworkList.add(ratingFramework);
            } catch (NoDataException e) {
                throw new RuntimeException(e);
            }
        });
        return frameworkList;
    }

    private RatingTable getRating(String ratingName) {
        return ratingTableRepo.findByRatingName(String.valueOf(Ratings.finder(ratingName)));
    }

    public void setQuestionWithRatingsList(Framework framework, FrameworkVO frameworkVO){
        List<RatingFrameworkVO> ratingFrameworkVOList = getFrameworkRatingsVOList(framework);
        framework.getFrameworkQuestions()
                .stream()
                .filter(question -> question.getQuestionStatus().equals(String.valueOf(Status.ACTIVE)))
                .sorted(Comparator.comparing(Question::getQuestionName))
                .forEach(question -> {
                    QuestionVO questionVO = new QuestionVO();
                    questionVO.setQuestionId(question.getQuestionId());
                    questionVO.setQuestionOrder(question.getQuestionOrder());
                    questionVO.setQuestionName(question.getQuestionName());
                    questionVO.setQuestionStatus(question.getQuestionStatus());
                    questionVO.setRatingFrameworkVOList(ratingFrameworkVOList);
                    frameworkVO.getFrameworkQuestions().add(questionVO);
                });
    }

    public List<RatingFrameworkVO> getFrameworkRatingsVOList(Framework framework){
        List<RatingFrameworkVO > ratingFrameworkVOList = new ArrayList<>();
        framework.getFrameworkRatings().stream()
                .filter(ratingFramework -> String.valueOf(Status.ACTIVE).equals(ratingFramework.getRatingFrameworkStatus()))
                .sorted(Comparator.comparing(RatingFramework::getRatingFrameworkScore))
                .forEach( frameworkRating -> {
                    RatingFrameworkVO ratingFrameworkVO = new RatingFrameworkVO();
                    ratingFrameworkVO.setFrameworkRatingId(frameworkRating.getRatingFrameworkId());
                    ratingFrameworkVO.setRatingFrameworkOrder(frameworkRating.getRatingFrameworkOrder());
                    ratingFrameworkVO.setRatingFrameworkScore(frameworkRating.getRatingFrameworkScore());
                    ratingFrameworkVO.setRatingFrameworkStatus(frameworkRating.getRatingFrameworkStatus());
                    setRatingTableData(ratingFrameworkVO, frameworkRating.getFrameworkRatings());
                    ratingFrameworkVOList.add(ratingFrameworkVO);
        });
        return  ratingFrameworkVOList;
    }
    public void setRatingTableData(RatingFrameworkVO frameworkVO, RatingTable ratingTable){
        RatingTableVO ratingTableVO = new RatingTableVO();
        ratingTableVO.setRatingId(ratingTable.getRatingId());
        ratingTableVO.setRatingKey(ratingTable.getRatingKey());
        ratingTableVO.setRatingName(ratingTable.getRatingName());
        ratingTableVO.setRatingOrder(ratingTable.getRatingOrder());
        ratingTableVO.setColour(ratingTable.getColour());
        ratingTableVO.setStatus(ratingTable.getStatus());
        frameworkVO.setFrameworkRatings(ratingTableVO);
    }
}
