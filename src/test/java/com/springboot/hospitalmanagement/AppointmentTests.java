package com.springboot.hospitalmanagement;

import com.springboot.hospitalmanagement.entity.Appointment;
import com.springboot.hospitalmanagement.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class AppointmentTests {

    @Autowired
    private AppointmentService appointmentService;

    //Creating new appointment and assigning to the doctor of patient
    @Test
    public void testCreateAppointment(){
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025,12,1,12,30))
                .reason("Cancer")
                .build();

        Appointment appointment1 = appointmentService.createNewAppointment(appointment,1L,3L);
        System.out.println(appointment1);

        //After that we are reassigning the same appointment to the new another doctor

        Appointment updatedAppointment = appointmentService.reAssignAppointmentToAnotherDoctor(appointment1.getId(),3L);

        System.out.println(updatedAppointment);
    }

    @Test
    public void testCreateAppointment2(){
        Appointment appointment1a = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2026,1,12,10,0))
                .reason("Knee Pain Issue")
                .build();

        Appointment appointment2a = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2026,2,12,10,0))
                .reason("Knee Pain Issue - Follow on checkup")
                .build();

        Appointment appointment3a = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2026,3,12,10,0))
                .reason("Knee Pain Issue- General Checkup")
                .build();

        Appointment appointment1 = appointmentService.createNewAppointment(appointment1a,3L,6L);
        Appointment appointment2 = appointmentService.createNewAppointment(appointment2a,3L,6L);
        Appointment appointment3 = appointmentService.createNewAppointment(appointment3a,3L,6L);

        for(Appointment appointmentChart : List.of(appointment1a,appointment2a,appointment3a)){
            System.out.println(appointmentChart);
        }
    }
}
