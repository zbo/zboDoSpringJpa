package org.zbo.done.repository;

import org.springframework.stereotype.Repository;
import org.zbo.done.model.Owner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by zbo on 4/21/16.
 */
@Repository
public class OwnerRepositoryImpl implements OwnerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Owner findById(int id) {
        Query query = this.em.createQuery("SELECT DISTINCT owner FROM Owner owner WHERE owner.id = :id");
        query.setParameter("id", id);
        return (Owner) query.getSingleResult();
    }
}
