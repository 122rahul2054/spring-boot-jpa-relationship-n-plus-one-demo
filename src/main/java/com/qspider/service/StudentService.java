
package com.qspider.service;

import com.qspider.dto.StudentRequest;
import com.qspider.model.Department;
import com.qspider.model.Profile;
import com.qspider.model.Student;
import com.qspider.repository.DepartmentRepository;
import com.qspider.repository.ProfileRepository;
import com.qspider.repository.StudentRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final ProfileRepository profileRepository;

    public StudentService(
            StudentRepository studentRepository,
            DepartmentRepository departmentRepository,
            ProfileRepository profileRepository) {

        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
        this.profileRepository = profileRepository;
    }

    // CREATE STUDENT
    @Transactional
    public Student createStudent(StudentRequest request) {

        Department department =
                departmentRepository.findById(
                        request.getDepartmentId());

        if (department == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Department not found with ID: "
                            + request.getDepartmentId());
        }

        Profile profile = new Profile();
        profile.setBio(request.getProfileBio());

        profileRepository.save(profile);

        Student student = new Student();
        student.setName(request.getName());
        student.setDepartment(department);
        student.setProfile(profile);

        return studentRepository.save(student);
    }

    // GET STUDENT BY ID
    @Transactional(readOnly = true)
    public Student fetchStudentById(Long id) {

        Student student = studentRepository.findById(id);

        if (student == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Student not found with ID: " + id);
        }

        return student;
    }

    // GET ALL STUDENTS
    @Transactional(readOnly = true)
    public List<Student> fetchAllStudents() {
        return studentRepository.findAll();
    }

    // UPDATE STUDENT
    @Transactional
    public Student updateStudent(
            Long id, StudentRequest request) {

        Student student = fetchStudentById(id);

        Department department =
                departmentRepository.findById(
                        request.getDepartmentId());

        if (department == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Department not found with ID: "
                            + request.getDepartmentId());
        }

        student.setName(request.getName());
        student.setDepartment(department);

        Profile profile = student.getProfile();

        if (profile == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Student profile not found");
        }

        profile.setBio(request.getProfileBio());

        profileRepository.update(profile);

        return studentRepository.update(student);
    }

    // DELETE STUDENT AND ITS PROFILE
    @Transactional
    public void removeStudent(Long id) {

        Student student = fetchStudentById(id);
        Profile profile = student.getProfile();

        studentRepository.delete(student);

        // Execute the student DELETE before deleting its profile.
        studentRepository.flush();

        if (profile != null) {
            profileRepository.delete(profile);
        }
    }
}
