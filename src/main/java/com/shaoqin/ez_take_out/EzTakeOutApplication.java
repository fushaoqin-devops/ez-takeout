package com.shaoqin.ez_take_out;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@EnableTransactionManagement
public class EzTakeOutApplication {

	public static void main(String[] args) {
		SpringApplication.run(EzTakeOutApplication.class, args);
		log.info("Application started...");
	}

}
