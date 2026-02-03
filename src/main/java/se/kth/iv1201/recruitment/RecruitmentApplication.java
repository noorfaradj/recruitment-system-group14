package se.kth.iv1201.recruitment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startpunkt för rekryteringsapplikationen.
 * Denna klass konfigurerar och startar Spring Boot-applikationen.
 */
@SpringBootApplication
public class RecruitmentApplication {

    /**
     * Huvudmetod som startar applikationen.
     *
     * @param args Argument från kommandoraden.
     */
    public static void main(String[] args) {
        SpringApplication.run(RecruitmentApplication.class, args);
    }
}