package org.zbo.done.repository;

import org.zbo.done.model.Owner;

import java.util.List;

/**
 * Created by zbo on 4/21/16.
 */
public interface OwnerRepository {
    Owner findById(int id);

    void save(Owner owner);

    List<Owner> findOwnerByLastName(String lastName);

    List<Owner>
    findAll();
}
