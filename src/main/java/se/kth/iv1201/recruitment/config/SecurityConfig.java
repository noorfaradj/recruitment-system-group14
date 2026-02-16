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
            // Inaktivera CSRF tillfälligt om du har problem med att POST-anrop (som register) blockeras
            // I en produktionmiljö bör detta vara på, men för labben underlättar det
            .csrf(csrf -> csrf.disable()) 
            
            .authorizeHttpRequests(auth -> auth
                // VIKTIGT: Se till att alla resurser som behövs för att visa sidorna är öppna
                .requestMatchers("/", "/register", "/login", "/css/**", "/js/**", "/error").permitAll()
                
                // Task 6: Roller. hasRole förväntar sig att din authority börjar på "ROLE_"
                .requestMatchers("/admin/**").hasRole("RECRUITER") 
                .requestMatchers("/apply/**").hasRole("APPLICANT") 
                
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login") 
                .loginProcessingUrl("/login") // Denna URL lyssnar Spring på vid inloggning
                .defaultSuccessUrl("/", true) 
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
        // Task 7: BCrypt för lösenordshashning
        return new BCryptPasswordEncoder();
    }
}