package se.kth.iv1201.recruitment.dto;

import java.time.LocalDate;

/**
 * DTO som representerar en tillgänglighetsrad
 * i ansökningsformuläret.
 * Innehåller start- och slutdatum.
 */
public class AvailabilityRowDTO {

    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * @return startdatum för tillgängligheten
     */
    public LocalDate getFromDate() {
        return fromDate;
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
     * @return slutdatum för tillgängligheten
     */
    public LocalDate getToDate() {
        return toDate;
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
