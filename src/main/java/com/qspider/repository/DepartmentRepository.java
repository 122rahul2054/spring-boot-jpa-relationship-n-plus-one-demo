
package com.qspider.repository;

import com.qspider.model.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Department save(Department department) {
        entityManager.persist(department);
        return department;
    }

    public Department update(Department department) {
        return entityManager.merge(department);
    }

    public Department findById(Long id) {
        return entityManager.find(Department.class, id);
    }

    public List<Department> findAll() {
        return entityManager.createQuery(
                "SELECT d FROM Department d ORDER BY d.id",
                Department.class
        ).getResultList();
    }

    public long countStudents(Long departmentId) {
        return entityManager.createQuery(
                        "SELECT COUNT(s) FROM Student s " +
                                "WHERE s.department.id = :departmentId",
                        Long.class
                ).setParameter("departmentId", departmentId)
                .getSingleResult();
    }

    public void delete(Department department) {
        entityManager.remove(department);
    }
}
