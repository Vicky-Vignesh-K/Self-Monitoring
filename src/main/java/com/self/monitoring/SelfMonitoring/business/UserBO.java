package com.self.monitoring.SelfMonitoring.business;

import com.self.monitoring.SelfMonitoring.configuration.NoDataException;
import com.self.monitoring.SelfMonitoring.dto.UserVO;
import com.self.monitoring.SelfMonitoring.entity.User;
import com.self.monitoring.SelfMonitoring.repository.IUserRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBO {

    @Autowired
    private IUserRepository userRepository;
    public UserVO saveUser(UserVO userVO){
        try {
            validateProvidedDetails(userVO);
            User user = new User();
            user.setUserEmail(userVO.getUserEmail());
            user.setUserName(userVO.getUserName());
            user.setUserPassword((userVO.getUserPassword()));
            user = userRepository.save(user);
            userVO.setUserId(user.getUserId());
        } catch (NoDataException e) {
            throw new RuntimeException(e);
        }
        return userVO;
    }
    public void validateProvidedDetails(UserVO userVO) throws NoDataException {
        if(StringUtils.isBlank(userVO.getUserEmail())){
            throw new NoDataException("User Email is Null or Blank");
        }
        if(StringUtils.isBlank(userVO.getUserName())){
            throw new NoDataException("User Name is Null or Blank");
        }
        if(StringUtils.isBlank(userVO.getUserPassword())){
            throw new NoDataException("User Password is Null or Blank");
        }
    }
}
