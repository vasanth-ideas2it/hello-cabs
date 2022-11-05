/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */

package com.hellocabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * <p>
 *   The overall control of application
 * </p>
 *
 * @author : Pradeep
 * created on 20/10/2022
 * @version 1.0
 *
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class HelloCabsApplication {

	/**
	 * <p>
	 *   Method where object creation and method call to access all
	 *   function in an application takes place
	 * </p>
	 *
	 * @param args command line argument
	 *
	 */
	public static void main(String[] args) {
		SpringApplication.run(HelloCabsApplication.class, args);
	}
}
