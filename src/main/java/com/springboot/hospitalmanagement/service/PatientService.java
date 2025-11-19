package com.springboot.hospitalmanagement.service;

import com.springboot.hospitalmanagement.repository.PatientRepository;
import com.springboot.hospitalmanagement.entity.Patient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    @Transactional
    public Patient getPatientById(Long id){

        Patient p1 = patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID not present"));
        Patient p2 = patientRepository.findById(id).orElseThrow(()-> new IllegalArgumentException(("Id is not present")));
        System.out.println(p1 == p2);

        p1.setName("koko");
        return p1;
    }

    public void deletePatientById(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow();
        patientRepository.delete(patient);
    }
}
