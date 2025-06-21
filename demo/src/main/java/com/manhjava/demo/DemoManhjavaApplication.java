package com.manhjava.demo;

// Import các class cần thiết từ Spring Boot
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Annotation này đánh dấu đây là một ứng dụng Spring Boot
@SpringBootApplication
public class DemoManhjavaApplication {

	// Hàm main - điểm bắt đầu của ứng dụng Java
	// SpringApplication.run sẽ khởi động ứng dụng Spring Boot
	public static void main(String[] args) {
		SpringApplication.run(DemoManhjavaApplication.class, args);
	}

}
