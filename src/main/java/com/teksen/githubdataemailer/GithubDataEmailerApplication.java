package com.teksen.githubdataemailer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GithubDataEmailerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubDataEmailerApplication.class, args);
	}

}
