package se.kth.iv1201.recruitment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import se.kth.iv1201.recruitment.dto.UserRegistrationDTO;
import se.kth.iv1201.recruitment.service.PersonService;

/**
 * Controller för publika sidor som inloggning och registrering.
 * Hanterar validering på server-sidan enligt Task 25.
 */
@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final PersonService personService;

    /**
     * Skapar en ny instans av {@code MainController}.
     *
     * @param personService service som hanterar registrering av användare
     */
    public MainController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Visar inloggningssidan.
     *
     * @return vynamn för login-sidan
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Visar registreringsformuläret.
     * Skapar ett tomt DTO-objekt för att Thymeleaf ska kunna binda fälten.
     *
     * @param model modellen som används för att skicka data till vyn
     * @return vynamn för register-sidan
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new UserRegistrationDTO());
        }
        return "register";
    }

    /**
     * Hanterar inskickat registreringsformulär.
     * @Valid triggar valideringsreglerna i UserRegistrationDTO.
     * BindingResult innehåller resultatet av valideringen (alla fel samtidigt).
     *
     * @param userDTO data från formuläret
     * @param result valideringsresultat
     * @param model modellen som används för att visa meddelanden i vyn
     * @return login-vy vid lyckad registrering, annars register-vy
     */
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserRegistrationDTO userDTO,
                               BindingResult result,
                               Model model) {

        // 1. Validering: Om fält saknas eller är felaktiga (t.ex. för kort lösenord)
        // Task 25: Server-side validation
        if (result.hasErrors()) {
            logger.warn("Validation failed for registration attempt: {} errors found", result.getErrorCount());
            return "register"; // Returnerar vyn med alla felmeddelanden synliga
        }

        try {
            // 2. Försök att registrera användaren i databasen
            personService.registerUser(userDTO);
            logger.info("Successfully registered new user: {}", userDTO.getUsername());

            // 3. Vid lyckad registrering, visa inloggningssidan med bekräftelse
            model.addAttribute("successMsg", "Registration successful! You can now log in.");
            return "login";

        } catch (Exception e) {
            // Hanterar om t.ex. användarnamnet redan finns (Task 12: Error handling)
            logger.error("Registration failed for user {}: {}", userDTO.getUsername(), e.getMessage());
            model.addAttribute("regError", "Username or email is already taken.");
            return "register";
        }
    }
}
