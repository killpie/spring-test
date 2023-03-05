package com.shuozhuoding.springtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpringTestApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringTestApplication.class, args);
	}

}
