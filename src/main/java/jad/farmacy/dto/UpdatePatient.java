package jad.farmacy.dto;

public class UpdatePatient {
    private long patientId;
    private String patientName;
    private String patientAge;
    private String patientPhone;
    private String patientAddress;
    private String description;
    private String physicExam;
    private String treatment;
    private String research;
    private String notes;
    private String reason;
    private String registeringDate;

    public String getRegisteringDate() {
        return registeringDate;
    }

    public void setRegisteringDate(String registeringDate) {
        this.registeringDate = registeringDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPhysicExam() {
        return physicExam;
    }

    public void setPhysicExam(String physicExam) {
        this.physicExam = physicExam;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getResearch() {
        return research;
    }

    public void setResearch(String research) {
        this.research = research;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
