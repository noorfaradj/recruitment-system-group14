package se.kth.iv1201.recruitment.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

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

    public Availability() {}
    public void setPerson(Person p) { this.person = p; }
    public void setFromDate(LocalDate d) { this.fromDate = d; }
    public void setToDate(LocalDate d) { this.toDate = d; }
}