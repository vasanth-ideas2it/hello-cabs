package com.hellocabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloCabsApplication {

	public static void main(String[] args) {

		try {
			SpringApplication.run(HelloCabsApplication.class, args);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}
}
