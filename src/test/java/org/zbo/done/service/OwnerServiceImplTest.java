package org.zbo.done.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.zbo.done.AbstractTestBase;
import org.zbo.done.model.Owner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by zbo on 4/21/16.
 */

public class OwnerServiceImplTest extends AbstractTestBase {

    @Autowired
    private OwnerService ownerService;

    @Test
    public void findOwnerById() throws Exception {
        Owner owner = this.ownerService.findOwnerById(1);
        assertThat(owner.getLastName()).startsWith("Franklin");
    }

}