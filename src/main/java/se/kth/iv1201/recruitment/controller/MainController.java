package se.kth.iv1201.recruitment.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.recruitment.dto.UserRegistrationDTO;
import se.kth.iv1201.recruitment.service.PersonService;

@Controller
public class MainController {
    private final PersonService personService;

    public MainController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/login")
    public String login() {
        return "login"; 
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserRegistrationDTO userDTO, BindingResult result, Model model) {
        // 1. Om fält saknas, visa formuläret igen med felmeddelanden (Scenario 5.1.3, steg 1a) [cite: 81]
        if (result.hasErrors()) {
            return "register"; 
        }
        
        // 2. Registrera kontot i databasen (Scenario 5.1.3, steg 2) [cite: 82]
        personService.registerUser(userDTO);
        
        // 3. Visa en bekräftelse (Scenario 5.1.3, steg 3) 
        // Istället för redirect till login, skickar vi med ett meddelande till en vy
        model.addAttribute("successMsg", "Registration successful! You can now log in.");
        return "login"; // Vi går till login men stannar på sidan för att visa meddelandet
    }
}