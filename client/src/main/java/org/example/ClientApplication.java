package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "org.example.models",
        "org.example.cli",
        "org.example.gui",
})
public class ClientApplication {
    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");

        SpringApplication.run(ClientApplication.class, args);
    }
}
