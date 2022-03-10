package com.gteam.glog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GlogServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlogServerApplication.class, args);
	}

}
