package jad.farmacy.controller;

import jad.farmacy.Service.PatientService;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.NewPatient;
import jad.farmacy.dto.UpdatePatient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse> getAllPatients() {
        return patientService.allPatients();
    }

    @PostMapping
    public ResponseEntity<GlobalResponse> addPatient(@RequestBody NewPatient newPatient) {
        return patientService.addPatient(newPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse> updatePatient( @RequestBody UpdatePatient updatePatient) {
        return patientService.updatePatient(updatePatient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse> deletePatientById(@PathVariable Long id) {
        return patientService.deletePatientById(id);
    }
}

