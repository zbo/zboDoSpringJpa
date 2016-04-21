package org.zbo.done.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.zbo.done.model.Owner;
import org.zbo.done.repository.OwnerRepository;

/**
 * Created by zbo on 4/21/16.
 */
@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public Owner findOwnerById(int id) throws DataAccessException {
        return this.ownerRepository.findById(id);
    }
}
