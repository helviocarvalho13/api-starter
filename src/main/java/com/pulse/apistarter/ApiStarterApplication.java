package com.pulse.apistarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

import com.pulse.apistarter.config.property.ApistarterProperty;

@SpringBootApplication
@EnableConfigurationProperties(ApistarterProperty.class)
@EnableCaching
public class ApiStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiStarterApplication.class, args);
	}

}
