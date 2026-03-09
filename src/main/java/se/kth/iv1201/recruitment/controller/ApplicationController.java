package se.kth.iv1201.recruitment.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se.kth.iv1201.recruitment.dto.ApplicationFormDTO;
import se.kth.iv1201.recruitment.repository.CompetenceRepository;
import se.kth.iv1201.recruitment.service.JobApplicationService;

/**
 * Kontrollerklass som hanterar webbanrop relaterade till ansökningsprocessen för arbetssökande.
 * Denna kontroller ansvarar för att visa ansökningsformuläret och hantera inskickad data
 * för kompetenser och tillgänglighetsperioder (Use Case 5.3).
 */
@Controller
@RequestMapping("/applicant")
public class ApplicationController {

    private final JobApplicationService service;
    private final CompetenceRepository competenceRepository;

    /**
     * Skapar en ny instans av {@code ApplicationController} med nödvändiga tjänster och repositories.
     *
     * @param service Tjänsten för hantering av jobbansökningar.
     * @param competenceRepository Repository för att hämta tillgängliga kompetenstyper.
     */
    public ApplicationController(JobApplicationService service, CompetenceRepository competenceRepository) {
        this.service = service;
        this.competenceRepository = competenceRepository;
    }

    /**
     * Visar ansökningsformuläret för den arbetssökande.
     * Metoden förbereder modellen med alla valbara kompetenser och ett tomt DTO-objekt
     * för databindning i vyn.
     *
     * @param model Modellen som används för att skicka data till Thymeleaf-vyn.
     * @return Sökvägen till Thymeleaf-mallen för ansökningssidan.
     */
    @GetMapping("/apply")
    public String showForm(Model model) {
        model.addAttribute("allCompetences", competenceRepository.findAll());
        model.addAttribute("form", new ApplicationFormDTO());
        return "applicant/apply";
    }

    /**
     * Hanterar inskickat ansökningsformulär och sparar informationen i databasen.
     * Processen inkluderar registrering av kompetensprofiler och tillgänglighetsperioder
     * knutna till den inloggade användaren.
     *
     * @param form DTO-objekt innehållande den data användaren fyllt i formuläret.
     * @param auth Autentiseringsobjektet som innehåller information om den inloggade användaren.
     * @return En redirect till den sökandes startsida med en framgångsparameter.
     */
    @PostMapping("/apply")
    public String submit(@ModelAttribute("form") ApplicationFormDTO form, Authentication auth) {
        if (auth != null && auth.isAuthenticated()) {
            service.saveFullApplication(form, auth.getName());
        }
        return "redirect:/applicant?success";
    }
}
