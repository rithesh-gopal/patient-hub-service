package org.rithesh.patienthubservice.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;


import java.time.LocalDate;

@Validated
public record PatientRecord(long id,
                            @NotBlank(message = "Name is required")
                            String name, String address,

                            @Pattern(regexp = "[0-9]{10}", message = "Phone number must be 10 digits")
                            String contactNumber,

                            @Email(message = "Email should be valid")
                            String email,

                            @Past(message = "Birth date must be in the past")
                            LocalDate dob,

                            boolean hasInsurance) {

}
