package com.brennan.learning.student;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student brennan = new Student(
				"Brennan",
				LocalDate.of(2004, FEBRUARY, 21),
				"brennandavenport9@gmail.com"
			);
            Student sean = new Student(
                "Sean",
                LocalDate.of(2009, OCTOBER, 27),
                "seandavenport3133@gmail.com"
            );

            repository.saveAll(
                List.of(brennan, sean)
            );
        };
    }
}
