package se.kth.iv1201.recruitment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

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

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Task 7: Obligatorisk BCrypt-hashing
        return new BCryptPasswordEncoder();
    }
}