package se.kth.iv1201.recruitment.dto;

/**
 * DTO som representerar en kompetensrad
 * i ansökningsformuläret.
 * Innehåller vald kompetens och antal års erfarenhet.
 */
public class CompetenceRowDTO {

    private Long competenceId;
    private Double years;

    /**
     * @return id för vald kompetens
     */
    public Long getCompetenceId() {
        return competenceId;
    }

    /**
     * Sätter kompetens-id.
     *
     * @param id kompetensens id
     */
    public void setCompetenceId(Long id) {
        this.competenceId = id;
    }

    /**
     * @return antal års erfarenhet
     */
    public Double getYears() {
        return years;
    }

    /**
     * Sätter antal års erfarenhet.
     *
     * @param y antal år
     */
    public void setYears(Double y) {
        this.years = y;
    }
}
