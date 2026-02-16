package se.kth.iv1201.recruitment.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    private String name;
    private String surname;
    private String pnr;
    private String email;
    private String username;
    private String password;

    @ManyToOne // Definierar relationen till Role-tabellen
    @JoinColumn(name = "role_id")
    private Role role;

    // Getters och Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; } // Fixar felet "undefined for the type Person"
    
    // Lägg till resterande setters för name, surname, pnr, email...
    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setPnr(String pnr) { this.pnr = pnr; }
    public void setEmail(String email) { this.email = email; }
}