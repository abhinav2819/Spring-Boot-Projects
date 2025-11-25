package com.springboot.hospitalmanagement.controller;

import com.springboot.hospitalmanagement.dto.AppointmentResponseDto;
import com.springboot.hospitalmanagement.dto.CreateAppointmentResponseDto;
import com.springboot.hospitalmanagement.dto.PatientResponseDto;
import com.springboot.hospitalmanagement.service.AppointmentService;
import com.springboot.hospitalmanagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;
    private final AppointmentService appointmentService;
    @GetMapping("/profile")
    public ResponseEntity<PatientResponseDto> getPatientProfile(Long patientId){
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }

    @PostMapping("/appointment")
    public ResponseEntity<AppointmentResponseDto>
    createNewAppointment(@RequestBody CreateAppointmentResponseDto createAppointmentResponseDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(appointmentService.createNewPatientAppointment(createAppointmentResponseDto));
    }
}
