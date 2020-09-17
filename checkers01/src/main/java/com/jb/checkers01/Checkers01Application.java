package com.jb.checkers01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Checkers01Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Checkers01Application.class, args);
		System.out.println("GO");

	}

}
