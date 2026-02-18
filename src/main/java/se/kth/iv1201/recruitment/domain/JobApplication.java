package se.kth.iv1201.recruitment.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "job_application")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER) // Ändrat till EAGER för att undvika LazyInitializationException
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "status", nullable = false)
    private String status = "UNHANDLED";

    public JobApplication() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Person getPerson() { return person; }
    public void setPerson(Person person) { this.person = person; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}