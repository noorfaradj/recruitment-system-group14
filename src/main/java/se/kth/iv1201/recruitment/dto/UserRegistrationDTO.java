package se.kth.iv1201.recruitment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO för användarregistrering.
 * Innehåller valideringsregler för att uppfylla Use Case 5.1.
 */
public class UserRegistrationDTO {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "First name is required")
    private String name;

    @NotBlank(message = "Last name is required")
    private String surname;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address format")
    private String email;

    @NotBlank(message = "Person number is required")
    private String pnr;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "The password itself needs to be at least 6 characters long")
    private String password;

    /**
     * Standardkonstruktor krävs för data binding.
     */
    public UserRegistrationDTO() {
    }

    /**
     * @return användarnamn
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sätter användarnamn.
     *
     * @param username användarnamn
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return förnamn
     */
    public String getName() {
        return name;
    }

    /**
     * Sätter förnamn.
     *
     * @param name förnamn
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return efternamn
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sätter efternamn.
     *
     * @param surname efternamn
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return e-postadress
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sätter e-postadress.
     *
     * @param email e-post
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return personnummer
     */
    public String getPnr() {
        return pnr;
    }

    /**
     * Sätter personnummer.
     *
     * @param pnr personnummer
     */
    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    /**
     * @return lösenord i klartext (hashas senare i service-lagret)
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sätter lösenord.
     *
     * @param password klartextlösenord
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
