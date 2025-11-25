package com.springboot.hospitalmanagement.dto;

import com.springboot.hospitalmanagement.entity.Department;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponseDto {
    private Long id;
    private String name;
    private String specialization;
    private String email;
}
