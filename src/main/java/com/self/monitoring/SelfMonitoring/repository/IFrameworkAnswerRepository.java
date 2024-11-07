package com.self.monitoring.SelfMonitoring.repository;

import com.self.monitoring.SelfMonitoring.entity.FrameworkAnswer;
import com.self.monitoring.SelfMonitoring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFrameworkAnswerRepository extends JpaRepository<FrameworkAnswer,Long> {

     List<FrameworkAnswer> findByUser(User User);
}
