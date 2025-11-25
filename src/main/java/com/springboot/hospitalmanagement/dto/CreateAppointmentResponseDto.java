package com.springboot.hospitalmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppointmentResponseDto {
    private Long doctorId;
    private Long patientId;
    private LocalDateTime appointmentTime;
    private String reason;

}
