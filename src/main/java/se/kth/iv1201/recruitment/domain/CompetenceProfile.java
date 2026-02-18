package se.kth.iv1201.recruitment.domain;

import jakarta.persistence.*;

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

    public CompetenceProfile() {}
    public void setPerson(Person p) { this.person = p; }
    public void setCompetence(Competence c) { this.competence = c; }
    public void setYears(Double y) { this.years = y; }
}