package org.zbo.done.service;

import org.springframework.dao.DataAccessException;
import org.zbo.done.model.Owner;
import org.zbo.done.repository.OwnerRepository;

/**
 * Created by zbo on 4/21/16.
 */
public interface OwnerService {

    Owner findOwnerById(int id) throws DataAccessException;

}
