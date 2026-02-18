package se.kth.iv1201.recruitment.exception;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global felhanterare för applikationen.
 * Fångar alla undantag som når controllernivån
 * och säkerställer att felet loggas samt att
 * användaren får en korrekt felsida.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Hanterar alla oförutsedda undantag.
     * Skapar ett unikt fel-ID, loggar stacktrace
     * och returnerar en användarvänlig felsida.
     *
     * @param exception undantaget som kastades
     * @param model modellen som skickas till vyn
     * @return namnet på felsidan
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception, Model model) {

        String errorId = UUID.randomUUID().toString();

        // Task 12: loggar felet med id och stacktrace
        logger.error("Error id: {} - {}", errorId, exception.getMessage(), exception);

        model.addAttribute("errorId", errorId);
        model.addAttribute("errorMessage",
                "An unexpected error occurred. Please contact support.");

        return "error";
    }
}
