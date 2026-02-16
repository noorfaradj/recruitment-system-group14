package se.kth.iv1201.recruitment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Konfigurationsklass för Spring Security.
 * Hanterar inloggning, sidåtkomst och lösenordshashning.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Definierar vilken lösenordskryptering som ska användas i applikationen.
     * Uppfyller 'Higher Grade Task 6' genom att använda BCrypt med arbetsfaktor 12.
     * Detta gör hashningen extremt resistent mot brute-force-attacker.
     *
     * @return En instans av BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    /**
     * Konfigurerar säkerhetsreglerna (vilka sidor som är publika vs skyddade).
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/register", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/recruiter/applications", true) // Hit skickas man efter inloggning
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll()
            );

        return http.build();
    }
}