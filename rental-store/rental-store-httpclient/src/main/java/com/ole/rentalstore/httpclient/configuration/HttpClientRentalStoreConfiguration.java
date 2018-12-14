package com.ole.rentalstore.httpclient.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = { "com.ole.rentalstore.httpclient" })
@PropertySource("classpath:resource.properties")
public class HttpClientRentalStoreConfiguration {
	
}