package se.kth.iv1201.recruitment.domain;

import jakarta.persistence.*;

/**
 * Entity som representerar en kompetensprofil.
 * Kopplar en person till en kompetens och antal års erfarenhet.
 * Mappas mot tabellen "competence_profile".
 */
@Entity
@Table(name = "competence_profile")
public class CompetenceProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competence_profile_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "competence_id")
    private Competence competence;

    @Column(name = "years_of_experience")
    private Double years;

    /**
     * Tom konstruktor krävs av JPA.
     */
    public CompetenceProfile() {
    }

    /**
     * @return id för kompetensprofilen
     */
    public Long getId() {
        return id;
    }

    /**
     * @return personen som kompetensen tillhör
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @return kompetensen
     */
    public Competence getCompetence() {
        return competence;
    }

    /**
     * @return antal års erfarenhet
     */
    public Double getYears() {
        return years;
    }

    /**
     * Sätter kopplad person.
     *
     * @param p person
     */
    public void setPerson(Person p) {
        this.person = p;
    }

    /**
     * Sätter kompetens.
     *
     * @param c kompetens
     */
    public void setCompetence(Competence c) {
        this.competence = c;
    }

    /**
     * Sätter antal års erfarenhet.
     *
     * @param y antal år
     */
    public void setYears(Double y) {
        this.years = y;
    }
}
