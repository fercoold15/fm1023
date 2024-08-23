package jad.farmacy.dto;


public class NewHistoricalVisit {

    private Long patientId;
    private String description;

    private String treatments;
    private String reason;
    private String notes;

    private String physicExam;

    private String research;

    public String getPhysicExam() {
        return physicExam;
    }

    public void setPhysicExam(String physicExam) {
        this.physicExam = physicExam;
    }

    public String getResearch() {
        return research;
    }

    public void setResearch(String research) {
        this.research = research;
    }

    public String getTreatments() {
        return treatments;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
