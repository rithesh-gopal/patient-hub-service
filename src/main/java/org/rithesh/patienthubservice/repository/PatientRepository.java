package org.rithesh.patienthubservice.repository;

import org.rithesh.patienthubservice.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

}
