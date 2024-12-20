package com.rosivaldolucas.magalu_connect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MagaluConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagaluConnectApplication.class, args);
	}

}
