package com.self.monitoring.SelfMonitoring.controller;

import com.self.monitoring.SelfMonitoring.business.FrameworkAnswerBO;
import com.self.monitoring.SelfMonitoring.business.FrameworkBO;
import com.self.monitoring.SelfMonitoring.business.ScoreFinderBO;
import com.self.monitoring.SelfMonitoring.configuration.NoDataException;
import com.self.monitoring.SelfMonitoring.configuration.ResponseStructure;
import com.self.monitoring.SelfMonitoring.dto.FrameworkAnswerVO;
import com.self.monitoring.SelfMonitoring.dto.FrameworkVO;
import com.self.monitoring.SelfMonitoring.dto.ScoreVO;
import com.self.monitoring.SelfMonitoring.dto.UserVO;
import com.self.monitoring.SelfMonitoring.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("selfMonitoring")
public class SelfMonitoringController {

    @Autowired
    private FrameworkBO business;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private FrameworkAnswerBO frameworkAnswerBO;

    @PostMapping("saveSelfMonitoring")
    public FrameworkVO saveSelfMonitoring(@RequestBody FrameworkVO data) {
        return business.save(data);
    }

    @PostMapping("fetchFrameworkById")
    public FrameworkVO fetchFrameworkById(@RequestBody FrameworkVO frameworkVO) throws NoDataException {
        return business.fetchFrameworkById(frameworkVO);
    }

    @PostMapping("saveFrameworkAnswer")
    public FrameworkAnswerVO saveFrameworkAnswer(@RequestBody FrameworkAnswerVO frameworkAnswerVO){
        return frameworkAnswerBO.saveFrameworkAnswer(frameworkAnswerVO);
    }

    @PostMapping("findFrameworkAnswerById")
    public FrameworkAnswerVO findFrameworkAnswerById(@RequestBody FrameworkAnswerVO frameworkAnswerVO){
        return frameworkAnswerBO.findFrameworkAnswerById(frameworkAnswerVO);
    }

    @PostMapping("findFrameworkAnswerByUserId")
    public ResponseEntity<ResponseStructure<ScoreVO>> findFrameworkAnswerByUserId(@RequestBody UserVO userVO){
       return scoreService.findUserAndCalculateScore(userVO);
    }

}
