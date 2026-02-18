package se.kth.iv1201.recruitment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Lägg till import

@SpringBootApplication
public class RecruitmentApplication {

    public static void main(String[] args) {
        // Denna rad skriver ut en giltig hash för lösenordet "password" i din terminal
        System.out.println("DEBUG - DIN NYA HASH: " + new BCryptPasswordEncoder().encode("password"));
        
        SpringApplication.run(RecruitmentApplication.class, args);
    }
}