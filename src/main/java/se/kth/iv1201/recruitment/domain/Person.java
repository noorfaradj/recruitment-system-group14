package se.kth.iv1201.recruitment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity som representerar en person i systemet.
 * Innehåller användarens personuppgifter, inloggningsuppgifter och roll.
 * Mappas mot tabellen "person".
 */
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

    /**
     * Tom konstruktor krävs av JPA.
     */
    public Person() {
    }

    /**
     * @return personens id
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * @return förnamn
     */
    public String getName() {
        return name;
    }

    /**
     * @return efternamn
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @return personnummer
     */
    public String getPnr() {
        return pnr;
    }

    /**
     * @return e-postadress
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return användarnamn
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return hashat lösenord
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return personens roll
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sätter personens id.
     *
     * @param personId id
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
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
     * Sätter efternamn.
     *
     * @param surname efternamn
     */
    public void setSurname(String surname) {
        this.surname = surname;
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
     * Sätter e-postadress.
     *
     * @param email e-post
     */
    public void setEmail(String email) {
        this.email = email;
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
     * Sätter lösenord.
     * Bör vara en hash (t.ex. BCrypt), inte klartext.
     *
     * @param password hashat lösenord
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sätter roll.
     *
     * @param role roll
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
