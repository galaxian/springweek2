package com.springweek2.springweek2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Springweek2Application {

    public static void main(String[] args) {
        SpringApplication.run(Springweek2Application.class, args);
    }

}
