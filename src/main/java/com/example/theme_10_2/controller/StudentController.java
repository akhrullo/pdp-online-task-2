package com.example.theme_10_2.controller;

import com.example.theme_10_2.entity.Address;
import com.example.theme_10_2.entity.Group;
import com.example.theme_10_2.entity.Student;
import com.example.theme_10_2.payload.StudentCreateDto;
import com.example.theme_10_2.payload.StudentUpdateDto;
import com.example.theme_10_2.repository.AddressRepository;
import com.example.theme_10_2.repository.GroupRepository;
import com.example.theme_10_2.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public record StudentController(StudentRepository studentRepository,
                                AddressRepository addressRepository,
                                GroupRepository groupRepository) {


    //1. VAZIRLIK
    @GetMapping("/forMinistry")
    public Page<Student> getStudentListForMinistry(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }

    //2. UNIVERSITY
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> getStudentListForUniversity(@PathVariable Integer universityId,
                                                     @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);
        return studentPage;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody StudentCreateDto dto) {
        Integer group_id = dto.getGroup_id();
        Integer address_id = dto.getAddress_id();

        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());

        Optional<Group> byId = groupRepository.findById(group_id);
        byId.ifPresent(student::setGroup);

        Optional<Address> byId1 = addressRepository.findById(address_id);
        byId1.ifPresent(student::setAddress);

        studentRepository.save(student);
        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        studentRepository.deleteById(id);
        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable Integer id) {
        Optional<Student> byId = studentRepository.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return (ResponseEntity<?>) ResponseEntity.notFound();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody StudentUpdateDto dto) {
        Integer group_id = dto.getGroup_id();
        Integer address_id = dto.getAddress_id();

        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setId(dto.getId());

        Optional<Group> byId = groupRepository.findById(group_id);
        byId.ifPresent(student::setGroup);

        Optional<Address> byId1 = addressRepository.findById(address_id);
        byId1.ifPresent(student::setAddress);

        studentRepository.save(student);
        return ResponseEntity.ok("success");
    }


}
