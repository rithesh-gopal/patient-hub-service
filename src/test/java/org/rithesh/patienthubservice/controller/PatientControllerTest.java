package org.rithesh.patienthubservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rithesh.patienthubservice.model.Patient;
import org.rithesh.patienthubservice.service.PatientService;
import org.rithesh.patienthubservice.service.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.util.NestedServletException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class PatientControllerTest {

    @Mock
    private PatientService patientService;
    @InjectMocks
    private PatientController patientController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
    }

    private static String getPatientString(){
        return """
                {
                    "name": "Rithesh",
                    "address": "Bengaluru",
                    "contactNumber": "9738548249",
                    "email": "rithesh@gmail.com",
                    "dob": "1994-04-25",
                    "hasInsurance": true
                }""";
    }

    private static String getInvalidPatientString(){
        return """
                {
                    "name": "Rithesh",
                    "address": "Bengaluru",
                    "contactNumber": "9738548249",
                    "email": "ritheshgmail.com",
                    "dob": "1994-04-25",
                    "hasInsurance": true
                }""";
    }

    @Test
    public void testCreatePatient_ValidPatient_Returns200() throws Exception {
        String requestBody = getPatientString();
        Patient patient = new Patient();

        doReturn(patient).when(patientService).createPatient(any());

        mockMvc.perform(post("/api/patients/")
                .contentType("application/json")
                .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreatePatient_InvalidPatient_Returns400() throws Exception {
        String requestBody = getInvalidPatientString();

        mockMvc.perform(post("/api/patients")
                .contentType("application/json")
                .content(requestBody))
                .andExpect(status().isBadRequest());
    }
}
