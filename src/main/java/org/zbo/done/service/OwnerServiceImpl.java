package org.zbo.done.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(readOnly = true)
    public Owner findOwnerById(int id) throws DataAccessException {
        return this.ownerRepository.findById(id);
    }

    @Override
    @Transactional
    public void saveOwner(Owner owner) throws DataAccessException {
        this.ownerRepository.save(owner);
    }
}
