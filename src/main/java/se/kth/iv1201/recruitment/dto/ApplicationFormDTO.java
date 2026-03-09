package se.kth.iv1201.recruitment.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO som representerar hela ansökningsformuläret.
 * Innehåller en lista av kompetenser och en lista av tillgänglighetsperioder.
 * Används för databindning i ansökningsvyn.
 */
public class ApplicationFormDTO {

    private List<CompetenceRowDTO> competences = new ArrayList<>();
    private List<AvailabilityRowDTO> availabilities = new ArrayList<>();

    /**
     * Skapar ett nytt formulär med en tom rad
     * för kompetens och tillgänglighet så att UI:t
     * alltid visar minst en rad.
     */
    public ApplicationFormDTO() {
        competences.add(new CompetenceRowDTO());
        availabilities.add(new AvailabilityRowDTO());
    }

    /**
     * @return lista av kompetensrader
     */
    public List<CompetenceRowDTO> getCompetences() {
        return competences;
    }

    /**
     * Sätter lista av kompetensrader.
     *
     * @param c lista med kompetenser
     */
    public void setCompetences(List<CompetenceRowDTO> c) {
        this.competences = c;
    }

    /**
     * @return lista av tillgänglighetsperioder
     */
    public List<AvailabilityRowDTO> getAvailabilities() {
        return availabilities;
    }

    /**
     * Sätter lista av tillgänglighetsperioder.
     *
     * @param a lista med perioder
     */
    public void setAvailabilities(List<AvailabilityRowDTO> a) {
        this.availabilities = a;
    }
}
