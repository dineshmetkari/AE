package com.stackroute.serviceadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//This is a special Annotation where Spring understands that main method is here

@SpringBootApplication
@EnableDiscoveryClient
public class MainClass {
	
	public static void main(String[] args) {
		// Default method to run Spring Boot Application
		SpringApplication.run(MainClass.class,args);
	}
}
