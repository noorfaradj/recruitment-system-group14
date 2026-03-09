package se.kth.iv1201.recruitment.exception;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.transaction.CannotCreateTransactionException;

/**
 * Centralized error handling class for the recruitment application.
 * This class captures and handles various types of exceptions and HTTP errors
 * across the entire application, ensuring that users always receive appropriate 
 * feedback instead of technical stack traces.
 * 
 */
@ControllerAdvice
@Controller
public class GlobalExceptionHandler implements ErrorController {

    /**
     * Handles general HTTP errors such as 404 (Not Found) and 403 (Forbidden).
     * * By implementing {@link ErrorController}, this method intercepts standard
     * servlet container errors before they are handled by the default white-label page.
     *
     * @param request The current HTTP request containing error details.
     * @param model   The model used to pass error messages to the view.
     * @return The name of the error view template (error.html).
     */
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            // handling for '404 - Not Found'
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorTitle", "404 - Page Not Found");
                model.addAttribute("errorMessage", "The page you are looking for does not exist.");
                model.addAttribute("status", "404");
                return "error";
            }
            
            // handling for '403 - Forbidden'
            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("errorTitle", "403 - Access Denied");
                model.addAttribute("errorMessage", "You do not have permission to access this page.");
                model.addAttribute("status", "403");
                return "error";
            }
        }
        
        // fallback for all other HTTP status codes
        model.addAttribute("errorTitle", "Internal Server Error");
        model.addAttribute("errorMessage", "The system is unable to process your request at this time. Please try again later.");
        model.addAttribute("status", status != null ? status.toString() : "500");
        return "error";
    }

    /**
     * Handles database-related malfunctions, specifically when the database is unreachable.
     * This method catches exceptions thrown by the persistence layer (Spring Data/JPA) 
     * and maps them to a user-friendly 503 Service Unavailable page.
     *
     * @param ex    The exception caught during database operations.
     * @param model The model used to pass error messages to the view.
     * @return The name of the error view template (error.html).
     */
    @ExceptionHandler({DataAccessException.class, CannotCreateTransactionException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String handleDatabaseMalfunction(Exception ex, Model model) {
        model.addAttribute("errorTitle", "System Unavailable");
        model.addAttribute("errorMessage", "Our database is currently unreachable. Please try again later.");
        model.addAttribute("status", "503");
        return "error";
    }
}