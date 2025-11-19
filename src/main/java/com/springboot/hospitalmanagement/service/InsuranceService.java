package com.springboot.hospitalmanagement.service;

import com.springboot.hospitalmanagement.entity.Insurance;
import com.springboot.hospitalmanagement.entity.Patient;
import com.springboot.hospitalmanagement.repository.InsuranceRepository;
import com.springboot.hospitalmanagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final PatientRepository patientRepository;

    private final InsuranceRepository insuranceRepository;

    //This method is used for assigning the insurance to the patient
    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("Patient not find with id "+patientId));
        patient.setInsurance(insurance);
        insurance.setPatient(patient); //bidirectional consistency maintenance

        return patient;
    }

    @Transactional
    public Patient disassociateInsuranceFromPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("Patient not find with id "+patientId));

        patient.setInsurance(null);
        return patient;
    }
}
