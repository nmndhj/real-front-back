package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing    //추가되어야 BaseEntity 활성화
@SpringBootApplication
public class DemoApplication {

	private static final String PROPERTIES =
			"spring.config.location="
					+ "classpath:/config/application/";

	public static void main(String[] args) {
		new SpringApplicationBuilder(DemoApplication.class)
				.properties(PROPERTIES)
				.run(args);
	}
}
