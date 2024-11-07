package com.self.monitoring.SelfMonitoring.repository;

import com.self.monitoring.SelfMonitoring.entity.RatingFramework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRatingFrameworkRepository extends JpaRepository<RatingFramework,Long> {
}
