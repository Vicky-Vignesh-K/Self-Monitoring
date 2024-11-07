package com.self.monitoring.SelfMonitoring.business;

import com.self.monitoring.SelfMonitoring.configuration.NoDataException;
import com.self.monitoring.SelfMonitoring.configuration.Status;
import com.self.monitoring.SelfMonitoring.dto.*;
import com.self.monitoring.SelfMonitoring.entity.*;
import com.self.monitoring.SelfMonitoring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FrameworkAnswerBO {
    @Autowired
    private ISelfMonitoringRepo frameworkRepo;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IFrameworkAnswerRepository frameworkAnswerRepository;

    @Autowired
    private IQuestionRepository questionRepository;

    @Autowired
    private IRatingFrameworkRepository ratingFrameworkRepository;

    public FrameworkAnswerVO saveFrameworkAnswer(FrameworkAnswerVO frameworkAnswerVO) {
        try {
            FrameworkAnswer frameworkAnswer = new FrameworkAnswer();
            validateMandatoryFields(frameworkAnswerVO);
            Optional<Framework> optionalFramework = frameworkRepo.findById(frameworkAnswerVO.getFrameworkVO().getFrameworkId());
            if (optionalFramework.isPresent()) {
                frameworkAnswer.setFramework(optionalFramework.get());
            } else {
                 validationException("No Framework data available for the given Framework Id : " + frameworkAnswerVO.getFrameworkVO().getFrameworkId());
            }
            Optional<User> optionalUser = userRepository.findById(frameworkAnswerVO.getUserVO().getUserId());
            if (optionalUser.isPresent()) {
                frameworkAnswer.setUser(optionalUser.get());
            } else {
                 validationException("User Details not available for the given User Id : " + frameworkAnswerVO.getUserVO().getUserId());
            }
            if (frameworkAnswerVO.getFrameworkAnswerStatus() == null || frameworkAnswerVO.getFrameworkAnswerStatus() == "")
                frameworkAnswer.setFrameworkAnswerStatus(String.valueOf(Status.ACTIVE));
            frameworkAnswer.setUserEnteredDate(frameworkAnswerVO.getUserEnteredDate());
            frameworkAnswer.setQuestionRatingList(getQuestionRatingList(frameworkAnswerVO.getQuestionRatingVOList(), frameworkAnswer));
            frameworkAnswer = frameworkAnswerRepository.save(frameworkAnswer);
            setFrameworkAnswerEntityToVO(frameworkAnswer, frameworkAnswerVO);
        } catch (NoDataException e) {
            throw new RuntimeException(e);
        }
        return frameworkAnswerVO;
    }

    private void setFrameworkAnswerEntityToVO(FrameworkAnswer frameworkAnswer, FrameworkAnswerVO frameworkAnswerVO) {
        frameworkAnswerVO.setFrameworkAnswerId(frameworkAnswer.getFrameworkAnswerId());
        frameworkAnswerVO.setFrameworkAnswerStatus(frameworkAnswer.getFrameworkAnswerStatus());
        frameworkAnswerVO.setUserEnteredDate(frameworkAnswer.getUserEnteredDate());
        frameworkAnswerVO.setSystemDate(frameworkAnswer.getSystemDate());
        frameworkAnswerVO.setFrameworkVO(setFrameworkToVO(frameworkAnswer.getFramework()));
        frameworkAnswerVO.setUserVO(setUserToVO(frameworkAnswer.getUser()));
        frameworkAnswerVO.setQuestionRatingVOList(setQuestionRatingListToVO(frameworkAnswer.getQuestionRatingList()));
    }

    private List<QuestionRatingVO> setQuestionRatingListToVO(List<QuestionRating> questionRatingList) {
        List<QuestionRatingVO> questionRatingVOList = new ArrayList<>();
        questionRatingList.forEach(questionRating -> {
            QuestionRatingVO questionRatingVO = new QuestionRatingVO();
            questionRatingVO.setQuestionRatingId(questionRating.getQuestionRatingId());
            questionRatingVO.setRatingFrameworkVO(setRatingFrameworkToVO(questionRating.getRatingFramework()));
            questionRatingVO.setQuestionVO(setQuestionToVO(questionRating.getQuestion()));
            questionRatingVOList.add(questionRatingVO);
        });
        return questionRatingVOList;
    }

    private QuestionVO setQuestionToVO(Question question) {
        QuestionVO questionVO = new QuestionVO();
        questionVO.setQuestionStatus(question.getQuestionStatus());
        questionVO.setQuestionId(question.getQuestionId());
        questionVO.setQuestionName(question.getQuestionName());
        questionVO.setQuestionOrder(question.getQuestionOrder());
        questionVO.setQuestionStatus(question.getQuestionStatus());
        return questionVO;
    }

    private RatingFrameworkVO setRatingFrameworkToVO(RatingFramework ratingFramework) {
        RatingFrameworkVO ratingFrameworkVO = new RatingFrameworkVO();
        ratingFrameworkVO.setRatingFrameworkScore(ratingFramework.getRatingFrameworkScore());
        ratingFrameworkVO.setRatingFrameworkStatus(ratingFramework.getRatingFrameworkStatus());
        ratingFrameworkVO.setFrameworkRatingId(ratingFramework.getRatingFrameworkId());
        ratingFrameworkVO.setRatingFrameworkOrder(ratingFramework.getRatingFrameworkOrder());
        ratingFrameworkVO.setFrameworkRatings(getRatingTableVOObj(ratingFramework.getFrameworkRatings()));
        return ratingFrameworkVO;
    }

    private UserVO setUserToVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setUserId(user.getUserId());
        userVO.setUserEmail(user.getUserEmail());
        userVO.setUserName(user.getUserName());
//        userVO.setUserPassword(user.getUserPassword());
        return userVO;
    }

    private FrameworkVO setFrameworkToVO(Framework framework) {
        FrameworkVO frameworkVO = new FrameworkVO();
        frameworkVO.setFrameworkStatus(framework.getFrameworkStatus());
        frameworkVO.setFrameworkId(framework.getFrameworkId());
        frameworkVO.setFrameworkDescription(framework.getFrameworkDescription());
        frameworkVO.setFrameworkName(framework.getFrameworkName());
        return frameworkVO;
    }

    private void validateMandatoryFields(FrameworkAnswerVO frameworkAnswerVO) throws NoDataException {
        if (frameworkAnswerVO.getFrameworkVO().getFrameworkId() == null || frameworkAnswerVO.getFrameworkVO().getFrameworkId() <= 0) {
            throw validationException("Framework id is Null or not a Positive Number");
        }
        if (frameworkAnswerVO.getUserVO().getUserId() == null || frameworkAnswerVO.getUserVO().getUserId() <= 0) {
            throw validationException("User id is Null or not a Positive Number");
        }
        if (frameworkAnswerVO.getUserEnteredDate() == null) {
            throw validationException("The Date field is Null or Empty");
        }
        if (CollectionUtils.isEmpty(frameworkAnswerVO.getQuestionRatingVOList())) {
            throw validationException("Question Rating List is Null or Empty");
        }
    }

    private List<QuestionRating> getQuestionRatingList(List<QuestionRatingVO> questionRatingVOList, FrameworkAnswer frameworkAnswer) {
        List<QuestionRating> questionRatingList = new ArrayList<>();
        questionRatingVOList.forEach(questionRatingVO -> {
            QuestionRating questionRating = new QuestionRating();
            questionRating.setQuestion(getQuestionObj(questionRatingVO.getQuestionVO()));
            questionRating.setFrameworkAnswer(frameworkAnswer);
            questionRating.setRatingFramework(getRatingFrameworkObj(questionRatingVO.getRatingFrameworkVO(), frameworkAnswer.getFramework()));
            questionRatingList.add(questionRating);
        });
        return questionRatingList;
    }

    private RatingFramework getRatingFrameworkObj(RatingFrameworkVO ratingFrameworkVO, Framework framework) {
        Optional<RatingFramework> optionalRatingFramework = ratingFrameworkRepository.findById(ratingFrameworkVO.getFrameworkRatingId());
        if (optionalRatingFramework.isPresent()) {
            RatingFramework ratingFramework = optionalRatingFramework.get();
            return ratingFramework;
        }
        return null;
    }

    private RatingTableVO getRatingTableVOObj(RatingTable ratingTable) {
        RatingTableVO ratingTableVO = new RatingTableVO();
        ratingTableVO.setRatingId(ratingTable.getRatingId());
        ratingTableVO.setRatingKey(ratingTable.getRatingKey());
        ratingTableVO.setRatingOrder(ratingTable.getRatingOrder());
        ratingTableVO.setRatingName(ratingTable.getRatingName());
        ratingTableVO.setStatus(ratingTable.getStatus());
        ratingTableVO.setColour(ratingTable.getColour());
        return ratingTableVO;
    }

    private Question getQuestionObj(QuestionVO questionVO) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionVO.getQuestionId());
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            return question;
        }
        return null;

    }

    private NoDataException validationException(String message) throws NoDataException {
        throw new NoDataException(message);
    }

    public FrameworkAnswerVO findFrameworkAnswerById(FrameworkAnswerVO frameworkAnswerVO){
        Optional<FrameworkAnswer> optionalFrameworkAnswer = frameworkAnswerRepository.findById(frameworkAnswerVO.getFrameworkAnswerId());
        if(optionalFrameworkAnswer.isPresent()){
            FrameworkAnswer frameworkAnswer = optionalFrameworkAnswer.get();
            frameworkAnswerVO.setUserVO(setUserToVO(frameworkAnswer.getUser()));
            frameworkAnswerVO.setUserEnteredDate(frameworkAnswer.getUserEnteredDate());
            frameworkAnswerVO.setFrameworkVO(setFrameworkToVO(frameworkAnswer.getFramework()));
            frameworkAnswerVO.setSystemDate(frameworkAnswer.getSystemDate());
            frameworkAnswerVO.setFrameworkAnswerStatus(frameworkAnswer.getFrameworkAnswerStatus());
            frameworkAnswerVO.setQuestionRatingVOList(setQuestionRatingListToVO(frameworkAnswer.getQuestionRatingList()));
        }
        return frameworkAnswerVO;
    }
}
