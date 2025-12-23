package com.project_management.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.project_management.demo.Config",
        "com.project_management.demo.service",
        "com.project_management.demo.controller",
        "com.project_management.demo.repository",
        "com.project_management.demo.Mapper",
        "com.project_management.demo.filter"
})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
