package jad.farmacy.dto;

public class UpdateHistoricalVisit {
    private Long historicalVisitId;
    private String visitDate;
    private Long patientId;
    private String description;

    public Long getHistoricalVisitId() {
        return historicalVisitId;
    }

    public void setHistoricalVisitId(Long historicalVisitId) {
        this.historicalVisitId = historicalVisitId;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
