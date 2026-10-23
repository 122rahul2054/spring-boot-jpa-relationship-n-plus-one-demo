
package com.qspider.repository;

import com.qspider.model.Profile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Profile save(Profile profile) {
        entityManager.persist(profile);
        return profile;
    }

    public Profile findById(Long id) {
        return entityManager.find(Profile.class, id);
    }

    public Profile update(Profile profile) {
        return entityManager.merge(profile);
    }

    public void delete(Profile profile) {
        entityManager.remove(profile);
    }

}
