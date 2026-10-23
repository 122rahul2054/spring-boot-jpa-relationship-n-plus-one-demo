
package com.qspider.service;

import com.qspider.model.Department;
import com.qspider.repository.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(
            DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public Department createDepartment(Department department) {
        department.setId(null);
        return departmentRepository.save(department);
    }

    @Transactional(readOnly = true)
    public Department fetchDepartmentById(Long id) {
        Department department = departmentRepository.findById(id);

        if (department == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Department not found with ID: " + id
            );
        }

        return department;
    }

    @Transactional(readOnly = true)
    public List<Department> fetchAllDepartments() {
        return departmentRepository.findAll();
    }

    @Transactional
    public Department updateDepartment(
            Long id, Department request) {

        Department department = fetchDepartmentById(id);
        department.setName(request.getName());

        return departmentRepository.update(department);
    }

    @Transactional
    public void removeDepartment(Long id) {
        Department department = fetchDepartmentById(id);

        long studentCount =
                departmentRepository.countStudents(id);

        if (studentCount > 0) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Cannot delete department because students " +
                            "are assigned to it"
            );
        }

        departmentRepository.delete(department);
    }
}
