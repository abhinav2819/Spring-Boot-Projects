package com.springboot.hospitalmanagement.service;

import com.springboot.hospitalmanagement.dto.DoctorResponseDto;
import com.springboot.hospitalmanagement.entity.Doctor;
import com.springboot.hospitalmanagement.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    private final ModelMapper modelMapper;

    public List<DoctorResponseDto> getAllDoctors() {
        List<Doctor> doctorList = doctorRepository.findAll();
        return doctorList
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorResponseDto.class))
                .collect(Collectors.toList());
    }
}
