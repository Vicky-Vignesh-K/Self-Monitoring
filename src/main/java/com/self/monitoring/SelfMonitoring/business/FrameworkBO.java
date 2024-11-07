package com.self.monitoring.SelfMonitoring.business;

import com.self.monitoring.SelfMonitoring.business.converter.Converter;
import com.self.monitoring.SelfMonitoring.configuration.NoDataException;
import com.self.monitoring.SelfMonitoring.configuration.Status;
import com.self.monitoring.SelfMonitoring.dto.FrameworkVO;
import com.self.monitoring.SelfMonitoring.entity.Framework;
import com.self.monitoring.SelfMonitoring.entity.RatingFramework;
import com.self.monitoring.SelfMonitoring.repository.IRatingTableRepo;
import com.self.monitoring.SelfMonitoring.repository.ISelfMonitoringRepo;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Component
public class FrameworkBO {
    @Autowired
    private ISelfMonitoringRepo repo;

    @Autowired
    private Converter converter;

    public FrameworkVO save(FrameworkVO data) {
        try {
            validation(data);
            Framework framework = new Framework();
            framework.setFrameworkDescription(data.getFrameworkDescription());
            if (data.getFrameworkStatus() == null)
                framework.setFrameworkStatus(String.valueOf(Status.ACTIVE));
            framework.setFrameworkName(data.getFrameworkName());
            converter.questionConverter(data.getFrameworkQuestions(), framework);
            List<RatingFramework> frameworkList = converter.ratingConverter(data.getFrameworkRatings(), framework);
            framework.setFrameworkRatings(frameworkList);
            repo.save(framework);
        } catch (NoDataException e) {
            e.getMessage();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return data;
    }

    private void validation(FrameworkVO data) throws NoDataException {
        if (StringUtils.isBlank(data.getFrameworkName())) {
            throw new NoDataException("Framework name is Empty or Blank");
        }
        if ((CollectionUtils.isEmpty(data.getFrameworkRatings()))) {
            throw new NoDataException("Framework Rating List is null or empty");
        }
        if (CollectionUtils.isEmpty(data.getFrameworkQuestions())) {
            throw new NoDataException("Question List is null or empty");
        }
    }

    public FrameworkVO fetchFrameworkById(FrameworkVO data) throws NoDataException {
        if(data.getFrameworkId()!=null || data.getFrameworkId()>0) {
            Optional<Framework> optionalFramework = repo.findById(data.getFrameworkId());
            if(optionalFramework.isPresent()) {
                Framework framework = optionalFramework.get();
                FrameworkVO frameworkVO = new FrameworkVO();
                frameworkVO.setFrameworkId(framework.getFrameworkId());
                frameworkVO.setFrameworkName(framework.getFrameworkName());
                frameworkVO.setFrameworkDescription(framework.getFrameworkDescription());
                frameworkVO.setFrameworkStatus(framework.getFrameworkStatus());
                converter.setQuestionWithRatingsList(framework, frameworkVO);
                return frameworkVO;
            }else throw new NoDataException("Data is not present for the Given Framework Id");
        }else throw new NoDataException("Framework Id is Null or less than Zero");
    }
}
