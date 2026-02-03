package se.kth.iv1201.recruitment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Recruitment Application.
 * This class bootstraps the Spring Boot application and starts the embedded server.
 */
@SpringBootApplication
public class RecruitmentApplication {

    /**
     * The main method that serves as the entry point for the Spring Boot application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(RecruitmentApplication.class, args);
    }
}