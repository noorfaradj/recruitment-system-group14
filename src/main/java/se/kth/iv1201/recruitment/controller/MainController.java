package se.kth.iv1201.recruitment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.iv1201.recruitment.dto.UserRegistrationDTO;
import se.kth.iv1201.recruitment.service.PersonService;

/**
 * Kontroller för oinloggade besökare. Hanterar startsida, inloggning och registrering.
 */
@Controller
public class MainController {

    private final PersonService personService;

    public MainController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Visar startsidan där användaren kan välja att logga in eller registrera sig.
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Visar inloggningssidan.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Visar registreringssidan.
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDTO());
        return "register";
    }

    /**
     * Tar emot och sparar en ny användare.
     */
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRegistrationDTO userDTO) {
        personService.registerUser(userDTO);
        return "redirect:/login?registered";
    }
}