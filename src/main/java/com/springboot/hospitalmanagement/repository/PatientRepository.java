package com.springboot.hospitalmanagement.repository;

import com.springboot.hospitalmanagement.dto.BloodGroupCountResponseEntity;
import com.springboot.hospitalmanagement.entity.Patient;
import com.springboot.hospitalmanagement.entity.type.BloodGroup;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findByName(String name);

    //For more method of query in Spring data JPA follow the documentation - https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

    Patient findByDateOfBirth(LocalDate dateOfBirth);

    List<Patient> findByDateOfBirthOrEmail(LocalDate dateOfBirth, String email);

    List<Patient> findByDateOfBirthAndEmail(LocalDate dataOfBirth, String email);

    List<Patient> findByDateOfBirthBetween(LocalDate startFrom, LocalDate endTo);

    List<Patient> findByDateOfBirthBetweenOrderByDateOfBirthDesc(LocalDate startDate, LocalDate endTo);

    List<Patient> findByNameContaining(String query);

    List<Patient> findByNameContainingOrderByIdDesc(String query);

    //WE are creating our own methods of JPA by writing the JPL Query on the methods

    @Query("SELECT p FROM Patient p WHERE p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup")BloodGroup bloodGroup);

    //Here at place of using the ?2 tag we can use the variable names also like shown below
    @Query("select p from Patient p where p.dateOfBirth > :dateOfBirth")
    List<Patient> findByBornAfterDate(@Param("dateOfBirth")LocalDate dateOfBirth);

    @Query("select p.bloodGroup, Count(p) from Patient p group by p.bloodGroup")
    List<Object[]> countEachBloodGroupType();

    //Above and below methods are ponting the same but the only difference is that we have created a class for the response
    //of the count of the blood group in above method we were just returning the object, so this way of representing data is
    //called as  - PROJECTION
    //Earlier we were returning array of array which make no sense when we try to convert into Json but not we can easily do so

    //Hear we have to define the classpath(Using package name) and call the constructor of the class having the fields inside it,
    // and then execute in JPL query

    @Query("select new com.springboot.hospitalmanagement.dto.BloodGroupCountResponseEntity(p.bloodGroup,"+
            " Count(p)) from Patient p group by p.bloodGroup")
    List<BloodGroupCountResponseEntity> countEachBloodGroupTypeByResponseEntity();

    @Query(value = "select * from patient_tbl order by id desc",nativeQuery = true)
    List<Patient> findAllPatients();

    @Query(value = "select * from patient_tbl",nativeQuery = true)
    List<Patient> findAllPatients(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Patient p set p.name = :name where p.id = :id")
    int updatePatientNameWithId(@Param("name") String name,@Param("id") Long id);

    @Query("SELECT p from Patient p LEFT JOIN FETCH p.appointment a LEFT JOIN FETCH a.doctor")
    List<Patient> findAllPatientsWithAppointment();
}
