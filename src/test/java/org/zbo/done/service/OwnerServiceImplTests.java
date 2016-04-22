package org.zbo.done.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.zbo.done.AbstractTestBase;
import org.zbo.done.model.Owner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by zbo on 4/21/16.
 */

public class OwnerServiceImplTests extends AbstractTestBase {

    @Autowired
    private OwnerService ownerService;

    @Test
    public void findOwnerByIdTest() throws Exception {
        Owner owner = this.ownerService.findOwnerById(1);
        assertThat(owner.getLastName()).startsWith("Franklin");
    }

    @Test
    public void saveOwnerTest() throws Exception {

        Owner owner = new Owner();
        owner.setFirstName("Sam");
        owner.setLastName("Schultz");
        owner.setAddress("4, Evans Street");
        owner.setCity("Wollongong");
        owner.setTelephone("4444444444");
        this.ownerService.saveOwner(owner);
        assertThat(owner.getId().longValue()).isNotEqualTo(0);

    }

}