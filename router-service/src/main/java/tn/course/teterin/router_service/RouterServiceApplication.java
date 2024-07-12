package tn.course.teterin.router_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication
@EnableFeignClients
public class RouterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouterServiceApplication.class, args);
	}

}
