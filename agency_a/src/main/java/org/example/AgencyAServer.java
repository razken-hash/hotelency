package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@EntityScan(basePackages = {
        "org.example.models"
})
@EnableJpaRepositories(basePackages = {
        "org.example.repositories"
})
@SpringBootApplication(scanBasePackages = {
        "org.example.services",
        "org.example.repositories",
        "org.example.models",
        "org.example.config",
})
public class AgencyAServer {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(AgencyAServer.class, args);
    }
}