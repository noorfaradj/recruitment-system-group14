package se.kth.iv1201.recruitment.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "competence")
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competence_id")
    private Long competenceId;

    private String name;

    public Competence() {}
    public Long getCompetenceId() { return competenceId; }
    public String getName() { return name; }
}