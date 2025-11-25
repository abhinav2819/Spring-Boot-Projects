package com.springboot.hospitalmanagement.service;

import com.springboot.hospitalmanagement.dto.AppointmentResponseDto;
import com.springboot.hospitalmanagement.dto.CreateAppointmentResponseDto;
import com.springboot.hospitalmanagement.dto.PatientResponseDto;
import com.springboot.hospitalmanagement.entity.Appointment;
import com.springboot.hospitalmanagement.repository.PatientRepository;
import com.springboot.hospitalmanagement.entity.Patient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    private final ModelMapper modelMapper;

    /*@Transactional
    public Patient getPatientById(Long id){

        Patient p1 = patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID not present"));
        Patient p2 = patientRepository.findById(id).orElseThrow(()-> new IllegalArgumentException(("Id is not present")));
        System.out.println(p1 == p2);

        p1.setName("koko");
        return p1;
    }*/

    public void deletePatientById(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow();
        patientRepository.delete(patient);
    }

    public List<PatientResponseDto> getAllPatients(Integer pageNumber, Integer pageSize) {
        List<Patient> patients = patientRepository.findAllPatients(PageRequest.of(pageNumber, pageSize));
        return patients
                .stream()
                .map(allPatients -> modelMapper.map(allPatients, PatientResponseDto.class))
                .collect(Collectors.toList());
    }

    public PatientResponseDto getPatientById(Long patientId) {
        Patient patient = patientRepository
                .findById(patientId)
                .orElseThrow(()->new IllegalArgumentException("Patient not found with id "+patientId));
        return modelMapper.map(patient,PatientResponseDto.class);
    }

}
