package com.self.monitoring.SelfMonitoring.repository;

import com.self.monitoring.SelfMonitoring.entity.RatingTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRatingTableRepo extends JpaRepository<RatingTable,Long> {
    public RatingTable findByRatingName(String name);
}
