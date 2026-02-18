package se.kth.iv1201.recruitment.dto;
import java.util.ArrayList;
import java.util.List;

public class ApplicationFormDTO {
    private List<CompetenceRowDTO> competences = new ArrayList<>();
    private List<AvailabilityRowDTO> availabilities = new ArrayList<>();

    public ApplicationFormDTO() {
        // Vi startar med en tom rad av varje för att visa i UI:t
        competences.add(new CompetenceRowDTO());
        availabilities.add(new AvailabilityRowDTO());
    }

    public List<CompetenceRowDTO> getCompetences() { return competences; }
    public void setCompetences(List<CompetenceRowDTO> c) { this.competences = c; }
    public List<AvailabilityRowDTO> getAvailabilities() { return availabilities; }
    public void setAvailabilities(List<AvailabilityRowDTO> a) { this.availabilities = a; }
}