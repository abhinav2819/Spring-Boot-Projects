package com.springboot.hospitalmanagement;

import com.springboot.hospitalmanagement.entity.Insurance;
import com.springboot.hospitalmanagement.entity.Patient;
import com.springboot.hospitalmanagement.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;


    @Test
    public void testInsuranceService(){
        Insurance insurance = Insurance.builder()
                .policyNumber("AXIS_1234")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030,12,31))
                .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance,2L);
        System.out.println(patient);

        var updatedPatient  = insuranceService.disassociateInsuranceFromPatient(patient.getId());
        System.out.println(updatedPatient);
    }
}
