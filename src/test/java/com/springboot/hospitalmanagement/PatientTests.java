package com.springboot.hospitalmanagement;

import com.springboot.hospitalmanagement.repository.PatientRepository;
import com.springboot.hospitalmanagement.dto.BloodGroupCountResponseEntity;
import com.springboot.hospitalmanagement.entity.Patient;
import com.springboot.hospitalmanagement.service.PatientService;
import com.springboot.hospitalmanagement.entity.type.BloodGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository(){

        List<Patient> patientList = patientRepository.findAll();
        System.out.println(patientList);

        Patient p1 = new Patient();
        patientRepository.save(p1);
    }

    //Hear we are trying to print the appointment also with patient
    @Test
    public void testPatientRepository2(){

        List<Patient> patientList = patientRepository.findAllPatientsWithAppointment();
        System.out.println(patientList);

    }

    @Test
    public void testTransactionMethod(){
        Patient patient = patientService.getPatientById(1L);

        System.out.println(patient);
    }

    @Test
    public void testJpaMethods(){
        Patient patient = patientRepository.findByName("Rani Kumari");
        System.out.println(patient);
    }

    @Test
    public void testJpaMethods2(){
        Patient patient = patientRepository.findByDateOfBirth(LocalDate.of(2005,07,21));
        System.out.println(patient);
    }

    @Test
    public void testJpaMethods3(){
        List<Patient> patientList = patientRepository.findByDateOfBirthOrEmail(LocalDate.of(2005,07,21),"shekhar@gmail.com");
        for(Patient patient : patientList){
            System.out.println(patient);
        }
    }

    @Test
    public void testJpaMethods4(){
        List<Patient> patientList = patientRepository.findByDateOfBirthAndEmail(LocalDate.of(2005,7,21),"kalavati@gmail.com");
        for(Patient patient : patientList){
            System.out.println(patient);
        }
    }

    @Test
    public void testJpaMethods5(){
        List<Patient> patientList = patientRepository
                .findByDateOfBirthBetween(LocalDate.of(1990,1,1),LocalDate.of(2002,12,31));
        for(Patient patient : patientList){
            System.out.println(patient);
        }
    }

    @Test
    public void testJpaMethods6(){
        List<Patient> patientList = patientRepository
                .findByDateOfBirthBetweenOrderByDateOfBirthDesc(LocalDate.of(1990,1,1),LocalDate.of(2002,12,31));
        for(Patient patient : patientList){
            System.out.println(patient);
        }
    }


    @Test
    public void testJpaMethods7(){
        List<Patient> patientList = patientRepository.findByNameContaining("Ra");
        for(Patient patient : patientList){
            System.out.println(patient);
        }
    }

    @Test
    public void testJpaMethods8(){
        List<Patient> patientList = patientRepository.findByNameContainingOrderByIdDesc("Ra");
        for(Patient patient : patientList){
            System.out.println(patient);
        }
    }

    @Test
    public void testJpaMethods9(){
        List<Patient> patientList = patientRepository.findByBloodGroup(BloodGroup.O_Positive);
        for(Patient patient : patientList){
            System.out.println(patient);
        }
    }

    @Test
    public void testJpaMethods10(){
        List<Patient> patientList = patientRepository.findByBornAfterDate(LocalDate.of(2000,12,30));
        for(Patient patient : patientList){
            System.out.println(patient);
        }
    }

    @Test
    public void testJpaMethods11(){
        List<Object[]> bloodGroupList = patientRepository.countEachBloodGroupType();
        for (Object[] object : bloodGroupList){
            System.out.println(object[0]+" "+object[1]);
        }
    }

    @Test
    public void testJpaMethods11a(){
        List<BloodGroupCountResponseEntity> bloodGroupList = patientRepository.countEachBloodGroupTypeByResponseEntity();
        for (BloodGroupCountResponseEntity bloodGroupCountResponseEntity : bloodGroupList){
            System.out.println(bloodGroupCountResponseEntity);
        }
    }

    @Test
    public void testJpaMethods12(){
        List<Patient> patientList = patientRepository.findAllPatients();
        for(Patient patient : patientList){
            System.out.println(patient);
        }
    }

    @Test
    public void testJpaMethods13(){
        int updatedRows = patientRepository.updatePatientNameWithId("Kajal Kumari", 3L);
        System.out.println(updatedRows);
    }

    @Test
    public void testDeletingPatientById(){
        patientService.deletePatientById(6L);
    }
}