package com.shaoqin.ez_take_out;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
public class EzTakeOutApplication {

	public static void main(String[] args) {
		SpringApplication.run(EzTakeOutApplication.class, args);
		log.info("Application started...");
	}

}
