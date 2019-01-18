package com.sample;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sample.utils.RESTContstants;

@EntityScan(basePackages = { "com.sample.*" })
@ComponentScan(basePackages = { "com.sample.*" })
@EnableJpaRepositories(basePackages = { "com.sample.*" })
@SpringBootApplication
public class AssetsUploaderApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone(RESTContstants.DEFAULT_TIMEZONE));
		SpringApplication.run(AssetsUploaderApplication.class, args);
	}

}

