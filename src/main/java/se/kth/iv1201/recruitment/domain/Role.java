package se.kth.iv1201.recruitment.domain;

import jakarta.persistence.*;

/**
 * Representerar en användarroll i systemet (t.ex. rekryterare eller sökande).
 * Mappar mot tabellen 'role' i databasen.
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

    protected Role() {}

    public Long getId() { return id; }
    public String getName() { return name; }
}