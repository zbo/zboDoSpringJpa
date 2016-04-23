package org.zbo.done.repository;

import org.springframework.stereotype.Repository;
import org.zbo.done.model.Owner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by zbo on 4/21/16.
 */
@Repository
public class OwnerRepositoryImpl implements OwnerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Owner findById(int id) {
        Query query = this.em.createQuery("SELECT DISTINCT owner FROM Owner owner " +
                "WHERE owner.id = :id");
        query.setParameter("id", id);
        return (Owner) query.getSingleResult();
    }

    @Override
    public void save(Owner owner) {
        if (owner.getId() == null) {
            this.em.persist(owner);
        } else {
            this.em.merge(owner);
        }
    }

    @Override
    public List<Owner> findOwnerByLastName(String lastName) {
        Query query = this.em.createQuery("SELECT DISTINCT owner FROM Owner owner " +
                "WHERE owner.lastName LIKE :lastName and owner.deleted =0");
        query.setParameter("lastName", lastName + "%");
        return query.getResultList();
    }

    @Override
    public List<Owner> findAll() {
        Query query = this.em.createQuery("SELECT DISTINCT owner FROM Owner owner " +
                "WHERE owner.deleted =0");
        return query.getResultList();
    }
}
