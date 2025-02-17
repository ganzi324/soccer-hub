package com.ganzi.soccerhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SoccerHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoccerHubApplication.class, args);
	}

}
