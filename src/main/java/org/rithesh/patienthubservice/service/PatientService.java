package org.rithesh.patienthubservice.service;

import org.rithesh.patienthubservice.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PatientService {

    List<Patient> getAllPatients();

    Patient getPatientById(Long id);

    Patient createPatient(Patient patient);

    Patient updatePatient(Long id, Patient patient);

    void deletePatient(Long id);


}
