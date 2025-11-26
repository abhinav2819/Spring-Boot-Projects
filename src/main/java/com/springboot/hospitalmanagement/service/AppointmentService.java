package com.springboot.hospitalmanagement.service;

import com.springboot.hospitalmanagement.dto.AppointmentResponseDto;
import com.springboot.hospitalmanagement.dto.CreateAppointmentResponseDto;
import com.springboot.hospitalmanagement.entity.Appointment;
import com.springboot.hospitalmanagement.entity.Doctor;
import com.springboot.hospitalmanagement.entity.Patient;
import com.springboot.hospitalmanagement.repository.AppointmentRepository;
import com.springboot.hospitalmanagement.repository.DoctorRepository;
import com.springboot.hospitalmanagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    //This method is used for the creation of the new appointment
    @Transactional
    @Secured("ROLE_PATIENT")
    public Appointment createNewAppointment(Appointment appointment, Long doctorId,Long patientId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have new id");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointment().add(appointment); //to maintain bidirectional consistency

        return appointmentRepository.save(appointment);
    }

    //This method is used for reassigning the appointment to tne another doctor
    @Transactional
    //We are using the granular level authorization level where we are using the method security
    //Hear we have used the spring expression language for retrieving the id of the doctor
    @PreAuthorize("hasAuthority('appointment:write') OR (#doctorId == authentication.principle.id)")
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor); //This will automatically call the update, because it is dirty now

        doctor.getAppointments().add(appointment);//just for bidirectional consistency

        return appointment;
    }

    public List<AppointmentResponseDto> getAllAppointmentsOfDoctor(Long doctorId) {
        Doctor doctor = doctorRepository
                .findById(doctorId)
                .orElseThrow(()->new IllegalArgumentException("Doctor not found with id "+doctorId));
        return doctor.getAppointments()
                .stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentResponseDto.class)).toList();
    }

    //We are using the granular level authorization level where we are using the method security
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('DOCTOR') AND #doctorId == authentication.principle.id)")
    public AppointmentResponseDto createNewPatientAppointment(CreateAppointmentResponseDto createAppointmentResponseDto) {
        Long doctorId = createAppointmentResponseDto.getDoctorId();
        Long patientId = createAppointmentResponseDto.getPatientId();

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        Appointment appointment = Appointment.builder()
                .appointmentTime(createAppointmentResponseDto.getAppointmentTime())
                .reason(createAppointmentResponseDto.getReason())
                .build();

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        patient.getAppointment().add(appointment); //To maintain consistency

        appointment = appointmentRepository.save(appointment);
        return modelMapper.map(appointment,AppointmentResponseDto.class);
    }
}
