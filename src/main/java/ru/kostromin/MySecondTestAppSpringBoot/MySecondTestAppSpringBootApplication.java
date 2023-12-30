package ru.kostromin.MySecondTestAppSpringBoot;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.kostromin.MySecondTestAppSpringBoot.service.RequestValidationService;

@SpringBootApplication
public class MySecondTestAppSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySecondTestAppSpringBootApplication.class, args);
	}
}
