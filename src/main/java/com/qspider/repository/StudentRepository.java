package com.qspider.repository;

import com.qspider.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Student save(Student student) {
        entityManager.persist(student);
        return student;
    }

    public Student update(Student student) {
        return entityManager.merge(student);
    }

    public Student findById(Long id) {
        return entityManager.createQuery(
                        "SELECT s FROM Student s " +
                                "JOIN FETCH s.department " +
                                "JOIN FETCH s.profile " +
                                "WHERE s.id = :id",
                        Student.class
                ).setParameter("id", id)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public List<Student> findAll() {
        return entityManager.createQuery(
                "SELECT s FROM Student s " +
                        "JOIN FETCH s.department " +
                        "JOIN FETCH s.profile " +
                        "ORDER BY s.id",
                Student.class
        ).getResultList();
    }

    public void delete(Student student) {
        entityManager.remove(student);
    }

    public void flush() {
        entityManager.flush();
    }
}
