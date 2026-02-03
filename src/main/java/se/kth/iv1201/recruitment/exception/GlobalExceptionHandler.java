package se.kth.iv1201.recruitment.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling application-wide exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles all exceptions and displays an error page.
     *
     * @param exception The exception that occurred.
     * @param model     The model to add error details to.
     * @return The name of the error page template.
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "error";
    }
}