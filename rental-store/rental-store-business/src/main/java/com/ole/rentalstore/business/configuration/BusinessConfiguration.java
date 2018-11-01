package com.ole.rentalstore.business.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ole.rentalstore.commons.configuration.CommonsConfiguration;
import com.ole.rentalstore.model.configuration.DatabaseConfiguration;

@Configuration
@ComponentScan(basePackages = { "com.ole.rentalstore.business" })
@Import( {DatabaseConfiguration.class, CommonsConfiguration.class} )
public class BusinessConfiguration {

}