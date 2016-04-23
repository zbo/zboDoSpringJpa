package org.zbo.done.service;

import org.springframework.dao.DataAccessException;
import org.zbo.done.model.Owner;

import java.util.List;

/**
 * Created by zbo on 4/21/16.
 */
public interface OwnerService {

    Owner findOwnerById(int id) throws DataAccessException;

    List<Owner> findOwnerByLastName(String lastName) throws DataAccessException;

    void deleteOwner(Owner owner) throws DataAccessException;

    void saveOwner(Owner owner) throws DataAccessException;

    List<Owner> findAll();
}
