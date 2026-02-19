package se.kth.iv1201.recruitment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.iv1201.recruitment.dto.UserRegistrationDTO;
import se.kth.iv1201.recruitment.service.PersonService;

/**
 * Controller som hanterar publika förfrågningar såsom inloggning och registrering.
 * <p>
 * Klassen fungerar som ingångspunkt i presentationslagret och delegerar 
 * affärslogik till {@link PersonService}.
 * </p>
 */
@Controller
public class MainController {

    private final PersonService personService;

    /**
     * Skapar en ny instans av MainController.
     *
     * @param personService servicelagret för hantering av personrelaterad affärslogik.
     */
    public MainController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Renderar inloggningssidan.
     *
     * @return namnet på Thymeleaf-mallen för inloggning.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Förbereder och visar registreringsformuläret.
     *
     * @param model modellobjekt som används för att binda ett tomt DTO till vyn.
     * @return namnet på Thymeleaf-mallen för registrering.
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new UserRegistrationDTO());
        }
        return "register";
    }

    /**
     * Tar emot och behandlar data från registreringsformuläret.
     * <p>
     * Vid ett eventuellt affärsfel (exempelvis upptaget användarnamn) fångas 
     * undantaget och ett felmeddelande presenteras för användaren.
     * </p>
     *
     * @param userDTO dataobjekt med användaruppgifter från formuläret.
     * @param model används för att skicka felmeddelanden tillbaka till vyn.
     * @return omdirigering till inloggning vid framgång, annars tillbaka till register-vyn.
     */
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserRegistrationDTO userDTO, Model model) {
        try {
            personService.registerUser(userDTO);
            model.addAttribute("successMsg", "Registration successful! You can now log in.");
            return "login";
        } catch (RuntimeException e) {
            model.addAttribute("regError", e.getMessage());
            return "register";
        }
    }
}