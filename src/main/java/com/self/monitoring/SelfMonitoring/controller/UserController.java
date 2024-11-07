package com.self.monitoring.SelfMonitoring.controller;

import com.self.monitoring.SelfMonitoring.business.UserBO;
import com.self.monitoring.SelfMonitoring.dto.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserBO userBusiness;

    @PostMapping("saveUser")
    public UserVO saveUser(@RequestBody UserVO userVO) {
        return userBusiness.saveUser(userVO);
    }
}
