package com.brennan.learning.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
	}

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
        } else {
            throw new IllegalStateException("Student with id: " + studentId + " does not exist");
        }
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new IllegalStateException("Student with id: " + studentId + " does not exist"));

        if (name != null && name.length() > 0 && !(student.getName().equals(name))){
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !(student.getEmail().equals(email)) && studentRepository.findStudentByEmail(email).isEmpty()) {
            student.setEmail(email);
        }
    }
}
