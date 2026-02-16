package se.kth.iv1201.recruitment.dto;

/**
 * DTO för att fånga in data från registreringsformuläret (Use Case 5.2).
 */
public class UserRegistrationDTO {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String pnr;
    private String email;

    // Getters & Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getPnr() { return pnr; }
    public void setPnr(String pnr) { this.pnr = pnr; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}