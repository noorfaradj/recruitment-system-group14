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

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Person() {} // JPA krav

    // Getters
    public Long getPersonId() { return personId; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getPnr() { return pnr; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }

    // Setters
    public void setPersonId(Long personId) { this.personId = personId; }
    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setPnr(String pnr) { this.pnr = pnr; }
    public void setEmail(String email) { this.email = email; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(Role role) { this.role = role; }
}