package com.payments.relational;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class  RelationalApplication {
	public static void main(String[] args) {
		SpringApplication.run(RelationalApplication.class, args);
	}
}
