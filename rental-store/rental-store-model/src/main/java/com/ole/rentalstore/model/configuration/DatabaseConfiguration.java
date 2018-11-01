package com.ole.rentalstore.model.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = { "com.ole.rentalstore.model.entities" })
@EnableJpaRepositories(basePackages = { "com.ole.rentalstore.model.repositories" })
public class DatabaseConfiguration {

}