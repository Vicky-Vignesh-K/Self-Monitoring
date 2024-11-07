package com.self.monitoring.SelfMonitoring.repository;

import com.self.monitoring.SelfMonitoring.entity.Framework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISelfMonitoringRepo extends JpaRepository<Framework,Long> {

}
