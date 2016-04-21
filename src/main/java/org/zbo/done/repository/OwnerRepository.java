package org.zbo.done.repository;

import org.zbo.done.model.Owner;

/**
 * Created by zbo on 4/21/16.
 */
public interface OwnerRepository {
    Owner findById(int id);
}
