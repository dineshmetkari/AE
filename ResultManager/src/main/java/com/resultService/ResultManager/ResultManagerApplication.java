package com.resultService.ResultManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ResultManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResultManagerApplication.class, args);
	}
}
