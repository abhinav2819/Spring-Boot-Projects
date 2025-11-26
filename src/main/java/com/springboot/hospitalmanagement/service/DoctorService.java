package com.springboot.hospitalmanagement.service;

import com.springboot.hospitalmanagement.dto.DoctorResponseDto;
import com.springboot.hospitalmanagement.dto.OnBoardDoctorRequestDto;
import com.springboot.hospitalmanagement.entity.Doctor;
import com.springboot.hospitalmanagement.entity.User;
import com.springboot.hospitalmanagement.entity.type.RoleType;
import com.springboot.hospitalmanagement.repository.DoctorRepository;
import com.springboot.hospitalmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public List<DoctorResponseDto> getAllDoctors() {
        List<Doctor> doctorList = doctorRepository.findAll();
        return doctorList
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorResponseDto.class))
                .collect(Collectors.toList());
    }

    public DoctorResponseDto onBoardNewDoctor(OnBoardDoctorRequestDto onBoardDoctorRequestDto) {
        User user = userRepository.findById(onBoardDoctorRequestDto.getUserId()).orElseThrow();

        if (doctorRepository.existsById(onBoardDoctorRequestDto.getUserId())){
            throw new IllegalArgumentException("Already a doctor");
        }

        Doctor doctor = Doctor.builder()
                .name(onBoardDoctorRequestDto.getName())
                .specialization(onBoardDoctorRequestDto.getSpecialization())
                .user(user)
                .build();

        user.getRoles().add(RoleType.DOCTOR);

        //We can do this in two ways

        /*doctor = doctorRepository.save(doctor);

        return modelMapper.map(doctor, DoctorResponseDto.class);*/

        return modelMapper.map(doctorRepository.save(doctor), DoctorResponseDto.class);
    }
}
