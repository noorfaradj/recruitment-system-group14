package se.kth.iv1201.recruitment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity som representerar en kompetens.
 * Mappas mot tabellen "competence".
 */
@Entity
@Table(name = "competence")
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competence_id")
    private Long competenceId;

    private String name;

    /**
     * Tom konstruktor krävs av JPA.
     */
    public Competence() {
    }

    /**
     * @return id för kompetensen
     */
    public Long getCompetenceId() {
        return competenceId;
    }

    /**
     * @return namn på kompetensen
     */
    public String getName() {
        return name;
    }
}
