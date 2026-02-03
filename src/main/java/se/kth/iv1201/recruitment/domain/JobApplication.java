package se.kth.iv1201.recruitment.domain;

import jakarta.persistence.*;

/**
 * Representerar en jobbansökan i databasen.
 * Denna entitet mappar mot tabellen 'job_application'.
 */
@Entity
@Table(name = "job_application")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "status", nullable = false)
    private String status;

    /**
     * Skapar en ny instans.
     * Detta är en tom konstruktor som krävs av JPA-ramverket.
     */
    protected JobApplication() {}

    /**
     * Hämtar ansökans unika identifierare.
     *
     * @return Ansökans ID.
     */
    public Long getId() { return id; }

    /**
     * Hämtar den sökandes förnamn.
     *
     * @return Förnamnet.
     */
    public String getFirstName() { return firstName; }

    /**
     * Hämtar den sökandes efternamn.
     *
     * @return Efternamnet.
     */
    public String getLastName() { return lastName; }

    /**
     * Hämtar nuvarande status för ansökan.
     *
     * @return Status som en sträng (t.ex. ACCEPTED, REJECTED, UNHANDLED).
     */
    public String getStatus() { return status; }
}