package com.self.monitoring.SelfMonitoring.repository;

import com.self.monitoring.SelfMonitoring.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionRepository extends JpaRepository<Question,Long> {
}
