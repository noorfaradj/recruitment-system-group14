package se.kth.iv1201.recruitment.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.UUID;

/**
 * Global felhanterare för applikationen.
 * Fångar alla undantag som kastar upp till controllernivån för att säkerställa
 * att användaren alltid får ett korrekt felmeddelande och att felet loggas.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Hanterar alla typer av oförutsedda undantag (Exceptions).
     * Genererar ett unikt fel-ID, loggar stacktracen och visar en användarvänlig felsida.
     *
     * @param exception Det undantag som kastades.
     * @param model     Modellen som används för att skicka data (fel-ID och meddelande) till vyn.
     * @return Namnet på felsidan som ska visas (error.html).
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception, Model model) {
        // skapa ett unikt id för felet
        String errorId = UUID.randomUUID().toString();

        // task 12: loggar felet med id och stacktrace
        logger.error("error id: " + errorId + " - " + exception.getMessage(), exception);

        // skickar engelskt meddelande och id till användaren
        model.addAttribute("errorId", errorId);
        model.addAttribute("errorMessage", "An unexpected error occurred. Please contact support.");
        
        return "error";
    }
}