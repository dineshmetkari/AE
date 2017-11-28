package com.stackroute.assessmentengine.userresultservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserResultServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserResultServiceApplication.class, args);
	}
}
