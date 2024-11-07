package com.self.monitoring.SelfMonitoring.business;

import com.self.monitoring.SelfMonitoring.configuration.NoDataException;
import com.self.monitoring.SelfMonitoring.dto.ScoreVO;
import com.self.monitoring.SelfMonitoring.dto.UserVO;
import com.self.monitoring.SelfMonitoring.entity.FrameworkAnswer;
import com.self.monitoring.SelfMonitoring.entity.User;
import com.self.monitoring.SelfMonitoring.repository.IFrameworkAnswerRepository;
import com.self.monitoring.SelfMonitoring.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class ScoreFinderBO {

    @Autowired
    private IFrameworkAnswerRepository frameworkAnswerRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ScoreVO scoreVO;

    public ScoreVO findScoreByUserId(UserVO userVO) {
        try {
            if (userVO != null) {
                if (userVO.getUserId() != null && userVO.getUserId() > 0) {
                    User user = new User();
                    user.setUserId(userVO.getUserId());
                    findUser(user);
                    calculatorScoreForUser(user);
                } else {
                    throw new NoDataException("User Id is Null or User Id should be a Positive Number");
                }
            } else {
                throw new NoDataException("User Object is Null");
            }
        } catch (NoDataException e) {
            throw new RuntimeException(e);
        }
        return scoreVO;
    }

    private ScoreVO calculatorScoreForUser(User user) throws NoDataException {
        List<FrameworkAnswer> frameworkAnswerList = frameworkAnswerRepository.findByUser(user);
        if (!CollectionUtils.isEmpty(frameworkAnswerList)) {
            double[] totalScore = {0.0};
            int noOfQuestions = frameworkAnswerList.get(0).getQuestionRatingList().size();
            Set<Double> maxScore = new HashSet<>();
            frameworkAnswerList.forEach(frameworkAnswer -> {
                frameworkAnswer.getQuestionRatingList().forEach(questionRating -> {
                    totalScore[0] += questionRating.getRatingFramework().getRatingFrameworkScore();
                });
                frameworkAnswer.getFramework()
                        .getFrameworkRatings().
                        forEach(ratingFramework -> maxScore.add(ratingFramework.getRatingFrameworkScore()));
            });
            double maxScoreVal = maxScore.stream().max(Double::compare).orElse(0.0);
            double finalPercent = (totalScore[0] / (maxScoreVal * noOfQuestions)) * 100;
            scoreVO.setMarksScored(totalScore[0]);
            scoreVO.setPercentage(finalPercent);
        } else {
            throw new NoDataException("Framework Answer List is Empty");
        }
        return scoreVO;
    }

    private void findUser(User user) throws NoDataException {
        Optional<User> optionalUser = userRepository.findById(user.getUserId());
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            scoreVO.setUserId(user.getUserId());
            scoreVO.setUserName(user.getUserName());
        } else {
            throw new NoDataException("User Not found for the given User Id");
        }
    }
}
