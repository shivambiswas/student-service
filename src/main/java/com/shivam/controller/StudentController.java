package com.shivam.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.shivam.model.Student;
import com.shivam.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    @RequestMapping("/allStudent")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping("/student/{id}")
    public Student getOneStudent(@PathVariable ("id") Long id) {
        return studentService.getSingleStudent(id);
    }

    @PostMapping("/addStudent")
    public String addStudent(@RequestBody List<Student> students) {
        return studentService.addStudent(students);
    }

    @PutMapping("/modifyStudent")
    public Student modifyStudent(@RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @PatchMapping("modifyPortionStudent")
    public Student modifyPortionOfStudent(@PathParam("id") Long id, @RequestBody JsonNode object) {
        return studentService.modifyPortionOfStudentObject(id, object);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable ("id") Long id) {
        studentService.deleteStudent(id);
        return "Deleted Successfully!";
    }

}
