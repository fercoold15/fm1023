package jad.farmacy.Service;

import jad.farmacy.Entity.HistoricalVisit;
import jad.farmacy.Entity.Patient;
import jad.farmacy.Exceptions.HistoricalVisitNotFoundException;
import jad.farmacy.Exceptions.PatientNotFoundException;
import jad.farmacy.Repository.HistoricalVisitRepository;
import jad.farmacy.Repository.PatientRepository;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.NewHistoricalVisit;
import jad.farmacy.dto.UpdateHistoricalVisit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistoricalVisitService {
    private final HistoricalVisitRepository historicalVisitRepository;
    private final PatientRepository patientRepository;
    ZoneId zoneId = ZoneId.of("UTC-6");

    public HistoricalVisitService(HistoricalVisitRepository historicalVisitRepository, PatientRepository patientRepository) {
        this.historicalVisitRepository = historicalVisitRepository;
        this.patientRepository = patientRepository;
    }

    public ResponseEntity<GlobalResponse> allHistoricalVisits() {
        List<HistoricalVisit> historicalVisits = new ArrayList<>();
        historicalVisitRepository.findAll().forEach(historicalVisits::add);
        GlobalResponse apiResponse = new GlobalResponse(200, "Registros Encontrados", "Registros Encontrados", historicalVisits);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> addHistoricalVisit(NewHistoricalVisit newHistoricalVisit) {
        Patient patient = patientRepository.findById(newHistoricalVisit.getPatientId())
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + newHistoricalVisit.getPatientId()));

        LocalDate date = LocalDate.from(LocalDateTime.now(zoneId));
        HistoricalVisit historicalVisit = new HistoricalVisit();
        historicalVisit.setVisitDate(date);
        historicalVisit.setPatient(patient);
        historicalVisit.setDescription(newHistoricalVisit.getDescription());
        historicalVisit.setTreatments(newHistoricalVisit.getTreatments());
        historicalVisit.setReason(newHistoricalVisit.getReason());
        historicalVisit.setNotes(newHistoricalVisit.getNotes());

        HistoricalVisit savedVisit = historicalVisitRepository.save(historicalVisit);
        GlobalResponse apiResponse = new GlobalResponse(200, "Visita Histórica Agregada", "Visita histórica agregada exitosamente", savedVisit);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> updateHistoricalVisit(UpdateHistoricalVisit updateHistoricalVisit) {
        if (updateHistoricalVisit.getHistoricalVisitId() == 0) {
            GlobalResponse apiResponse = new GlobalResponse(400, "Error", "ID de visita histórica inválido", null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        Patient patient = patientRepository.findById(updateHistoricalVisit.getPatientId())
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + updateHistoricalVisit.getPatientId()));
        LocalDate date = LocalDate.from(LocalDateTime.now(zoneId));
        HistoricalVisit historicalVisit = new HistoricalVisit();
        historicalVisit.setId(updateHistoricalVisit.getHistoricalVisitId());
        historicalVisit.setVisitDate(date);
        historicalVisit.setPatient(patient);
        historicalVisit.setDescription(updateHistoricalVisit.getDescription());
        historicalVisit.setTreatments(updateHistoricalVisit.getTreatments());
        historicalVisit.setReason(updateHistoricalVisit.getReason());
        historicalVisit.setNotes(updateHistoricalVisit.getNotes());

        HistoricalVisit updatedVisit = historicalVisitRepository.save(historicalVisit);
        GlobalResponse apiResponse = new GlobalResponse(200, "Visita Histórica Actualizada", "Visita histórica actualizada exitosamente", updatedVisit);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> getHistoricalVisitById(Long id) {
        HistoricalVisit historicalVisit = historicalVisitRepository.findById(id)
                .orElseThrow(() -> new HistoricalVisitNotFoundException("Historical visit not found with id: " + id));
        GlobalResponse apiResponse = new GlobalResponse(200, "Visita Histórica Encontrada", "Visita histórica encontrada exitosamente", historicalVisit);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> deleteHistoricalVisitById(Long id) {
        if (historicalVisitRepository.existsById(id)) {
            historicalVisitRepository.deleteById(id);
            GlobalResponse apiResponse = new GlobalResponse(200, "Visita Histórica Eliminada", "Visita histórica eliminada exitosamente", null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            GlobalResponse apiResponse = new GlobalResponse(404, "Error", "Visita histórica no encontrada con id: " + id, null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }
}

