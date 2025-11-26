package com.springboot.hospitalmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 50)
    private String name;

    @Column(length = 100)
    private String specialization;

    @Column(unique = true,length = 150)
    @Email
    private String email;

    @OneToOne
    @MapsId
    private User user;

    @ManyToMany(mappedBy = "doctors")
    @ToString.Exclude
    private Set<Department> department = new HashSet<>();

    @OneToMany(mappedBy = "doctor")
    @ToString.Exclude
    private List<Appointment> appointments = new ArrayList<>();

}
