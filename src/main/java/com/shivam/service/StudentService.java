package com.shivam.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.shivam.dao.StudentRepository;
import com.shivam.exception.StudentNotFoundException;
import com.shivam.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepo;
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getSingleStudent(Long id) throws StudentNotFoundException{
        Student student = studentRepo.findById(id).orElse(null);
        if(Objects.isNull(student))
            throw new StudentNotFoundException("404","Student was not found for the given id!");
        return student;
    }

    public String addStudent(List<Student> students) {
        Student singleStudent = null;
        List<Student> listOfStudent = null;
        if(students.size()==1) {
            singleStudent = studentRepo.saveAndFlush(students.get(0));
            if(Objects.isNull(singleStudent))
                return "Couldn't save Student/Students!";
        }
        else {
            listOfStudent = studentRepo.saveAllAndFlush(students);
            if(Objects.isNull(listOfStudent))
                return "Couldn't save Student/Students!";
            }
            return "Saved Successfully!";
    }

    public Student editStudent(Student student) {
        Student dbStudent = studentRepo.findById(student.getId())
                .orElseThrow(()-> new StudentNotFoundException("Error","Not found"));
        student.setId(dbStudent.getId());
        return studentRepo.save(student);

    }

    public Student modifyPortionOfStudentObject(Long id, JsonNode studentObject) {
        Student dbStudent = studentRepo.findById(id)
                .orElseThrow(()-> new StudentNotFoundException("Error","Not found"));
        if(studentObject.has("name"))
            dbStudent.setName(studentObject.findValue("name").asText());
        if(studentObject.has("roll"))
            dbStudent.setRoll(studentObject.findValue("roll").asInt());
        if(studentObject.has("email"))
            dbStudent.setEmail(studentObject.findValue("email").asText());
        if(studentObject.has("totalMarks"))
            dbStudent.setTotalMarks(studentObject.findValue("totalMarks").asInt());
        return studentRepo.save(dbStudent);
    }

    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }

}
