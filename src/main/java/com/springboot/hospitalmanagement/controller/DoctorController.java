package com.springboot.hospitalmanagement.controller;

import com.springboot.hospitalmanagement.dto.AppointmentResponseDto;
import com.springboot.hospitalmanagement.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

    private final AppointmentService appointmentService;

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsOfDoctor(Long doctorId){
        return ResponseEntity.ok(appointmentService.getAllAppointmentsOfDoctor(2L));
    }
}
