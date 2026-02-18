package se.kth.iv1201.recruitment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startklass för applikationen.
 *
 * Innehåller main-metoden som startar Spring Boot
 * och initierar hela systemet.
 */
@SpringBootApplication
public class RecruitmentApplication {

    /**
     * Startar applikationen.
     *
     * @param args kommandoradsargument
     */
    public static void main(String[] args) {
        SpringApplication.run(RecruitmentApplication.class, args);
    }
}
