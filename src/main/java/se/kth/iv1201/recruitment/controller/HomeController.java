package se.kth.iv1201.recruitment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitment.repository.JobApplicationRepository;

/**
 * Controller som hanterar landningssidor efter inloggning och listning av ansökningar.
 * Uppfyller kraven för Use Case 5.2 och 5.4.
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final JobApplicationRepository jobApplicationRepository;

    public HomeController(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }
    @GetMapping("/home")
    public String home(Authentication authentication) {
        boolean isRecruiter = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_RECRUITER"));
    
        if (isRecruiter) {
            return "redirect:/recruiter"; // Skickar admin till /recruiter
        }
        return "redirect:/applicant"; // Skickar sökande till /applicant
    }
    
    // Sidan man landar på som Recruiter
    @GetMapping("/recruiter")
    public String recruiterDashboard(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        return "admin-dashboard"; 
    }
    
    // Sidan man landar på som Applicant
    @GetMapping("/applicant")
    public String applicantHome(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        return "applicant-home"; 
    }

}