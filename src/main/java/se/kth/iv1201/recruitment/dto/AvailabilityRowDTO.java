package se.kth.iv1201.recruitment.dto;
import java.time.LocalDate;
public class AvailabilityRowDTO {
    private LocalDate fromDate;
    private LocalDate toDate;
    // Getters/Setters
    public LocalDate getFromDate() { return fromDate; }
    public void setFromDate(LocalDate d) { this.fromDate = d; }
    public LocalDate getToDate() { return toDate; }
    public void setToDate(LocalDate d) { this.toDate = d; }
}