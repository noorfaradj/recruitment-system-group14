package se.kth.iv1201.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.kth.iv1201.recruitment.service.JobApplicationService;

/**
 * Controller for handling recruiter-related operations.
 */
@Controller
public class RecruiterController {

    private final JobApplicationService jobApplicationService;

    /**
     * Creates an instance of RecruiterController.
     *
     * @param jobApplicationService The service handling job application logic.
     */
    @Autowired
    public RecruiterController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    /**
     * Handles HTTP GET requests to list all job applications.
     *
     * @param model The model to which application data is added.
     * @return The name of the Thymeleaf template to render.
     */
    @GetMapping("/recruiter/applications")
    public String listApplications(Model model) {
        model.addAttribute("applications", jobApplicationService.listAllApplications());
        return "recruiter/applications";
    }
}