package se.kth.iv1201.recruitment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representerar en användarroll i systemet (t.ex. rekryterare eller sökande).
 * Mappas mot tabellen "role" i databasen.
 */
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "name")
    private String name;

    /**
     * Skyddad konstruktor krävs av JPA.
     */
    protected Role() {
    }

    /**
     * @return rollens id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return rollens namn (t.ex. ROLE_RECRUITER eller ROLE_APPLICANT)
     */
    public String getName() {
        return name;
    }
}
