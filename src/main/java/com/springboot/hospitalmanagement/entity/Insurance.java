package com.springboot.hospitalmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String policyNumber;

    @Column(nullable = false, length = 500)
    private String provider;

    @Column(nullable = false)
    private LocalDate validUntil;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    //Hear we have excluded this field due to solving the error of stack overflow exception where insurance and patient field
    //defined in each Entity keeps calling each other and lost in loop and stack goes overflowed so to prevent this we
    //have to exclude anyone of the field from the Entity to run the toString() method easily

    @ToString.Exclude
    @OneToOne(mappedBy = "insurance") // inverse side
    private Patient patient;

}
