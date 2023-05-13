package com.example.vueljoy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VueljoyApplication {

	public static void main(String[] args) {
		SpringApplication.run(VueljoyApplication.class, args);
	}

	@Bean
	public int questionNo()
	{
		return 0;
	}

}
