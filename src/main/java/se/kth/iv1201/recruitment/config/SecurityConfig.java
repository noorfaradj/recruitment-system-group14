package se.kth.iv1201.recruitment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security-konfiguration för applikationen.
 * Här definieras regler för åtkomst (authorize), login, logout, och lösenords-hashning.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Konfigurerar Spring Security filter chain.
     * Styr vilka endpoints som är publika, och vilka som kräver inloggning/roller.
     *
     * @param http HttpSecurity som används för att bygga säkerhetsreglerna
     * @return en färdig SecurityFilterChain
     * @throws Exception om konfigurationen misslyckas
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // CSRF inaktiverad för att underlätta POST från formulär under labben
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/register", "/login", "/css/**", "/js/**", "/error").permitAll()

                // Ändra här! Matchar nu kontrollerns @RequestMapping("/applicant")
                .requestMatchers("/applicant/**").hasRole("APPLICANT")

                // Ändra här om din RecruiterController använder /recruiter/...
                .requestMatchers("/recruiter/**").hasRole("RECRUITER")

                .requestMatchers("/home").authenticated()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login") // Viktigt: URL dit inloggningsformuläret postar
                .defaultSuccessUrl("/home", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }

    /**
     * Skapar en PasswordEncoder som hashar lösenord med BCrypt.
     * Detta gör att lösenord inte lagras i klartext.
     *
     * @return PasswordEncoder baserad på BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Task 7: Obligatorisk BCrypt-hashing
        return new BCryptPasswordEncoder();
    }
}
