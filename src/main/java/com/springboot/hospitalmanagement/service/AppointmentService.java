package com.springboot.hospitalmanagement.service;

import com.springboot.hospitalmanagement.entity.Appointment;
import com.springboot.hospitalmanagement.entity.Doctor;
import com.springboot.hospitalmanagement.entity.Patient;
import com.springboot.hospitalmanagement.repository.AppointmentRepository;
import com.springboot.hospitalmanagement.repository.DoctorRepository;
import com.springboot.hospitalmanagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    //This method is used for the creation of the new appointment
    @Transactional
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
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor); //This will automatically call the update, because it is dirty now

        doctor.getAppointments().add(appointment);//just for bidirectional consistency

        return appointment;
    }
}
