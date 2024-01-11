package com.alimama.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.alimama.common.entity","com.alimama.admin.user"})
public class AecBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(AecBackEndApplication.class, args);
	}

}
