package com.springboot.hospitalmanagement.dto;

import com.springboot.hospitalmanagement.entity.type.BloodGroup;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BloodGroupCountResponseEntity {
    private BloodGroup bloodGroup;
    private Long count;
}
