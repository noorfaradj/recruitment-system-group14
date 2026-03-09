package se.kth.iv1201.recruitment.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller som hanterar landningssidor efter inloggning.
 * Denna klass styr användaren till rätt dashboard baserat på roll (Use Case 5.2).
 */
@Controller
public class HomeController {

    /**
     * Dirigerar inloggade användare till rätt startsida baserat på deras roll.
     *
     * @param authentication Innehåller information om den inloggade användarens auktoriteter.
     * @return En redirect till antingen /recruiter eller /applicant.
     */
    @GetMapping("/home")
    public String home(Authentication authentication) {
        boolean isRecruiter = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_RECRUITER"));

        if (isRecruiter) {
            return "redirect:/recruiter";
        }
        return "redirect:/applicant";
    }

    /**
     * Visar dashboarden för rekryterare.
     *
     * @param authentication Inloggad användare
     * @param model Modell för vyn
     * @return vynamn för rekryterarens dashboard
     */
    @GetMapping("/recruiter")
    public String recruiterDashboard(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        return "admin-dashboard";
    }

    /**
     * Visar hemvyn för sökande.
     *
     * @param authentication Inloggad användare
     * @param model Modell för vyn
     * @return vynamn för sökandens hemvy
     */
    @GetMapping("/applicant")
    public String applicantHome(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        return "applicant-home";
    }
}
