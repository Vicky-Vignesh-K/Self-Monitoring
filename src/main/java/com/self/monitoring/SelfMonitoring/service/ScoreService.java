package com.self.monitoring.SelfMonitoring.service;

import com.self.monitoring.SelfMonitoring.business.ScoreFinderBO;
import com.self.monitoring.SelfMonitoring.configuration.ResponseStructure;
import com.self.monitoring.SelfMonitoring.dto.ScoreVO;
import com.self.monitoring.SelfMonitoring.dto.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    @Autowired
    private ScoreFinderBO scoreFinderBO;

    @Autowired
    ResponseStructure responseStructure;

    public ResponseEntity<ResponseStructure<ScoreVO>> findUserAndCalculateScore(UserVO userVO){
        ScoreVO  scoreVO = scoreFinderBO.findScoreByUserId(userVO);
        if(scoreVO != null){
            responseStructure.setData(scoreVO);
            responseStructure.setMessage("User Score has been successfully Calculated");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(responseStructure,HttpStatus.OK);
        }else{
            responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Error in Calculating User score");
//            responseStructure.setData(scoreVO);
            return new ResponseEntity<>(responseStructure,HttpStatus.NOT_FOUND);
        }

    }
}
