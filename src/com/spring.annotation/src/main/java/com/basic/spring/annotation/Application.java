package com.basic.spring.annotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
/*
Enables these three annotation
	@Configuration -> Treats all subclasses as configuration files
	@ComponentScan -> searches for Components in sub packages
	@EnableAutoConfiguration -> Directs spring to guess dependencies from jars if not specified
 */
public class Application {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
//		Checking beans
		System.out.println(context.getBean(CakeMaker.class).getCake());
	}
}
