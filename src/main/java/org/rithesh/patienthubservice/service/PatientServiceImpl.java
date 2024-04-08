package org.rithesh.patienthubservice.service;

import exception.PatientNotFoundException;
import org.rithesh.patienthubservice.model.Patient;
import org.rithesh.patienthubservice.repository.PatientRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }


    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        for(Patient patient: patientRepository.findAll()){
            patients.add(patient);
        }
        return patients;
    }

    @Override
    @Cacheable(value = "patients", key = "#id")
    public Patient getPatientById(Long id) throws PatientNotFoundException{
        return patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));
    }

    @Override
    @CachePut(value = "patients", key = "#patient.id")
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    @CachePut(value = "patients", key = "#id")
    public Patient updatePatient(Long id, Patient patient) {
        patient.setId(id);
        return patientRepository.save(patient);
    }

    @Override
    @CacheEvict(value = "patients", key = "#id")
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
