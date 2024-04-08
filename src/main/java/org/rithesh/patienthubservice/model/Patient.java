package org.rithesh.patienthubservice.model;

import jakarta.persistence.*;
import org.rithesh.patienthubservice.records.PatientRecord;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    private String address;

    private String contactNumber;

    private String email;

    @Temporal(TemporalType.DATE)
    private LocalDate dob;

    private boolean hasInsurance;

    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments;

    @OneToMany(mappedBy = "patient")
    private Set<Prescription> prescriptions;

    public Patient(){

    }

    public Patient(long id, String name, String address, String contactNumber, String email, LocalDate  dob, boolean hasInsurance) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
        this.dob = dob;
        this.hasInsurance = hasInsurance;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate  getDob() {
        return dob;
    }

    public void setDob(LocalDate  dob) {
        this.dob = dob;
    }

    public boolean isHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
}
