package se.kth.iv1201.recruitment.dto;

import jakarta.validation.constraints.*;

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

    // Standardkonstruktor krävs för data binding
    public UserRegistrationDTO() {}

    // Getters och Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPnr() { return pnr; }
    public void setPnr(String pnr) { this.pnr = pnr; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}