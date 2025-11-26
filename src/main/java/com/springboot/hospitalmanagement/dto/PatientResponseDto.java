package com.springboot.hospitalmanagement.dto;

import com.springboot.hospitalmanagement.entity.type.BloodGroup;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private String gender;
    private BloodGroup bloodGroup;
}
