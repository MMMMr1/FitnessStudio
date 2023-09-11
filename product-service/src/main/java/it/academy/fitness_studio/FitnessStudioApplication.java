package it.academy.fitness_studio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
@EnableEurekaClient
public class FitnessStudioApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessStudioApplication.class, args);
	}

}
