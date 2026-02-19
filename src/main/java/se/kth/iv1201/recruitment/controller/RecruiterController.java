package se.kth.iv1201.recruitment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import se.kth.iv1201.recruitment.service.JobApplicationService;

/**
 * Hanterar webbanrop för rekryteringsvyn.
 * Loggar inkommande trafik enligt kraven i Task 12.
 */
@Controller
public class RecruiterController {

    private final JobApplicationService jobApplicationService;

    /**
     * Skapar en ny instans av {@code RecruiterController}.
     *
     * @param jobApplicationService service som hanterar jobbansökningar
     */
    public RecruiterController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    /**
     * Hämtar alla jobbansökningar och visar dem på en lista.
     * Uppfyller Task 12 genom att logga inkommande förfrågningar.
     *
     * @param model Modellen som används för att skicka data till vyn.
     * @return Namnet på Thymeleaf-mallen som ska renderas.
     */
    @GetMapping("/recruiter/applications")
    public String listApplications(Model model) {
        model.addAttribute("applications", jobApplicationService.listAllApplications());
        return "recruiter/applications";
    }
}
