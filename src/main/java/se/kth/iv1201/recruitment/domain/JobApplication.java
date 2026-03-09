package se.kth.iv1201.recruitment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity som representerar en jobbansökan.
 * Kopplar en person till en ansökan och dess status.
 * Mappas mot tabellen "job_application".
 */
@Entity
@Table(name = "job_application")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Personen som har skickat in ansökan.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    /**
     * Status för ansökan.
     * Standardvärde är "UNHANDLED".
     */
    @Column(name = "status", nullable = false)
    private String status = "UNHANDLED";

    /**
     * Tom konstruktor krävs av JPA.
     */
    public JobApplication() {
    }

    /**
     * @return id för ansökan
     */
    public Long getId() {
        return id;
    }

    /**
     * Sätter id.
     *
     * @param id ansöknings-id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return personen som skickade ansökan
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Sätter kopplad person.
     *
     * @param person person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * @return nuvarande status för ansökan
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sätter status.
     *
     * @param status ny status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
