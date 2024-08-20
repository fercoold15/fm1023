package jad.farmacy.Entity;

import jakarta.persistence.*;

@Table(name = "PATIENTS")
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PATIENT_ID",nullable = false)
    private Long id;
    @Column(name = "PATIENT_NAME")
    private String patientName;

    @Column(name = "PATIENT_AGE")
    private String patientAge;

    @Column(name = "PATIENT_PHONE")
    private String patientPhone;

    @Column(name = "PATIENT_ADDRESS")
    private String patientAddress;
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
    @Lob
    @Column(name = "PHYSIC_EXAM")
    private String physicExam;
    @Lob
    @Column(name = "TREATMENT")
    private String treatment;
    @Lob
    @Column(name = "RESEARCH")
    private String research;

    @Lob
    @Column(name = "NOTES")
    private String notes;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
