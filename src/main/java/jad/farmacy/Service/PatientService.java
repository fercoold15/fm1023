package jad.farmacy.Service;

import jad.farmacy.Entity.Patient;
import jad.farmacy.Exceptions.PatientNotFoundException;
import jad.farmacy.Repository.PatientRepository;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.NewPatient;
import jad.farmacy.dto.UpdatePatient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public ResponseEntity<GlobalResponse> allPatients() {
        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patients::add);
        GlobalResponse apiResponse = new GlobalResponse(200, "Registros Encontrados", "Registros Encontrados", patients);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> addPatient(NewPatient newPatient) {
        Patient patient = new Patient();
        patient.setPatientName(newPatient.getPatientName());
        patient.setPatientPhone(newPatient.getPatientPhone());
        patient.setPatientAddress(newPatient.getPatientAddress());
        patient.setDescription(newPatient.getDescription());

        Patient savedPatient = patientRepository.save(patient);
        GlobalResponse apiResponse = new GlobalResponse(200, "Paciente Agregado", "Paciente agregado exitosamente", savedPatient);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> updatePatient(UpdatePatient updatePatient) {
        if (updatePatient.getPatientId() == 0) {
            GlobalResponse apiResponse = new GlobalResponse(400, "Error", "ID de paciente inválido", null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        Patient patient = new Patient();
        patient.setId(updatePatient.getPatientId());
        patient.setPatientName(updatePatient.getPatientName());
        patient.setPatientPhone(updatePatient.getPatientPhone());
        patient.setPatientAddress(updatePatient.getPatientAddress());
        patient.setDescription(updatePatient.getDescription());

        Patient updatedPatient = patientRepository.save(patient);
        GlobalResponse apiResponse = new GlobalResponse(200, "Paciente Actualizado", "Paciente actualizado exitosamente", updatedPatient);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("PatientRepository not found with id: " + id));
        GlobalResponse apiResponse = new GlobalResponse(200, "Paciente Encontrado", "Paciente encontrado exitosamente", patient);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> deletePatientById(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            GlobalResponse apiResponse = new GlobalResponse(200, "Paciente Eliminado", "Paciente eliminado exitosamente", null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            GlobalResponse apiResponse = new GlobalResponse(404, "Error", "Paciente no encontrado con id: " + id, null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }
}
