package com.ole.rentalstore.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.ole.rentalstore.business.configuration.BusinessConfiguration;
import com.ole.rentalstore.commons.configuration.CommonsConfiguration;

@SpringBootApplication
@Import({ BusinessConfiguration.class, CommonsConfiguration.class })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}