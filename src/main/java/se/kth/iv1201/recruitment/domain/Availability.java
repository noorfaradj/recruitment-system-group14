package se.kth.iv1201.recruitment.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity som representerar en tillgänglighetsperiod
 * för en arbetssökande.
 *
 * Mappas mot tabellen "availability".
 */
@Entity
@Table(name = "availability")
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availability_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    /**
     * Tom konstruktor krävs av JPA.
     */
    public Availability() {
    }

    /**
     * @return availability-id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return personen som tillgängligheten tillhör
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @return startdatum för tillgänglighet
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * @return slutdatum för tillgänglighet
     */
    public LocalDate getToDate() {
        return toDate;
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
     * Sätter startdatum.
     *
     * @param d startdatum
     */
    public void setFromDate(LocalDate d) {
        this.fromDate = d;
    }

    /**
     * Sätter slutdatum.
     *
     * @param d slutdatum
     */
    public void setToDate(LocalDate d) {
        this.toDate = d;
    }
}
