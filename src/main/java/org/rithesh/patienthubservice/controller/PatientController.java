package org.rithesh.patienthubservice.controller;

import jakarta.validation.Valid;
import org.rithesh.patienthubservice.model.Patient;
import org.rithesh.patienthubservice.records.PatientRecord;
import org.rithesh.patienthubservice.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patients")
@Validated
public class PatientController {



    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }


    @GetMapping
    public ResponseEntity<List<PatientRecord>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        List<PatientRecord> patientRecords = patients.stream()
                .map(this::convertToPatientRecord)
                .collect(Collectors.toList());
        return ResponseEntity.ok(patientRecords);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientRecord> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(convertToPatientRecord(patient));
    }

    @PostMapping
    public ResponseEntity<PatientRecord> createPatient(  @RequestBody @Valid PatientRecord patientRecord) {
        Patient patient = convertToPatient(patientRecord);
        Patient createdPatient = patientService.createPatient(patient);
        PatientRecord createdPatientRecord = convertToPatientRecord(createdPatient);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatientRecord);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientRecord> updatePatient(@PathVariable Long id, @Valid @RequestBody PatientRecord patientRecord) {
        Patient patient = convertToPatient(patientRecord);
        Patient updatedPatient = patientService.updatePatient(id, patient);
        PatientRecord updatedPatientRecord = convertToPatientRecord(updatedPatient);
        return ResponseEntity.ok(updatedPatientRecord);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    private Patient convertToPatient(PatientRecord patientRecord) {
        return  new Patient(patientRecord.id(), patientRecord.name(), patientRecord.address(), patientRecord.contactNumber(), patientRecord.email(), patientRecord.dob(), patientRecord.hasInsurance());
    }

    private PatientRecord convertToPatientRecord(Patient patient) {
        return  new PatientRecord(patient.getId(), patient.getName(), patient.getAddress(), patient.getContactNumber(), patient.getEmail(), patient.getDob(), patient.isHasInsurance());

    }

}
