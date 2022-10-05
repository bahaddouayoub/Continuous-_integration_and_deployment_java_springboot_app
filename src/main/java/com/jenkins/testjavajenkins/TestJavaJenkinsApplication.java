package com.jenkins.testjavajenkins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class TestJavaJenkinsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestJavaJenkinsApplication.class, args);

		
	}
	@RestController
public class ControllerSer{

	@GetMapping("/home")
	public String getHello(){
		return "hello from java app ";
		}
}
	
}
