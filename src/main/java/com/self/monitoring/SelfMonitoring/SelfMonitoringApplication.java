package com.self.monitoring.SelfMonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.self.monitoring.SelfMonitoring")
public class SelfMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelfMonitoringApplication.class, args);
	}

}
