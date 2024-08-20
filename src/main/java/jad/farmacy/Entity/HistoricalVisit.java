package jad.farmacy.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
@Table(name = "HISTORICAL_VISITS")
@Entity
public class HistoricalVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORICAL_VISIT_ID",nullable = false)
    private Long id;

    @Column(name = "VISIT_DATE")
    private LocalDate visitDate;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID", referencedColumnName = "PATIENT_ID", nullable = false)
    private Patient patient;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TREATMENTS")
    private String treatments;

    @Column(name = "REASON")
    private String reason;

    @Column(name = "notes")
    private String notes;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
