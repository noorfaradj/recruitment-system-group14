package se.kth.iv1201.recruitment.domain;

import jakarta.persistence.*;

/**
 * Entity representing a job application in the system.
 */
@Entity
@Table(name = "job_application")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    /**
     * Gets the ID of the job application.
     *
     * @return the application ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the applicant's first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the applicant's last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the status of the job application.
     *
     * @return the application status
     */
    public ApplicationStatus getStatus() {
        return status;
    }
}
