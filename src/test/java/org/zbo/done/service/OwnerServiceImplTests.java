package org.zbo.done.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.zbo.done.AbstractTestBase;
import org.zbo.done.model.Owner;
import org.zbo.done.util.EntityUtils;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by zbo on 4/21/16.
 */

public class OwnerServiceImplTests extends AbstractTestBase {

    @Autowired
    private OwnerService ownerService;

    @Test
    public void findAllOwnersTest() throws Exception {
        List<Owner> owners = this.ownerService.findAll();
        Owner owner2 = EntityUtils.getById(owners, Owner.class, 2);
        assertThat(owner2.getDeleted()).isEqualTo(0);
        assertThat(owner2.toString()).isNotEmpty();
        assertThat(owner2.getAddress()).isEqualTo("638 Cardinal Ave.");
        Owner owner5 = EntityUtils.getById(owners, Owner.class, 5);
        assertThat(owner5.getLastName()).isEqualTo("McTavish");
        Owner owner6 = EntityUtils.getById(owners, Owner.class, 6);
        assertThat(owner6.getCity()).isEqualTo("Monona");
        Owner owner7 = EntityUtils.getById(owners, Owner.class, 7);
        assertThat(owner7.getTelephone()).isEqualTo("6085555387");
    }

    @Test
    public void findOwnerByIdTest() throws Exception {
        Owner owner = this.ownerService.findOwnerById(1);
        assertThat(owner.getLastName()).startsWith("Franklin");
    }

    @Test
    public void saveOwnerTest() throws Exception {
        List<Owner> owners = this.ownerService.findOwnerByLastName("Schultz");
        int found = owners.size();

        Owner owner = new Owner();
        owner.setId(null);
        assertThat(owner.isNew()).isTrue();

        owner.setFirstName("Sam");
        owner.setLastName("Schultz");
        owner.setAddress("4, Evans Street");
        owner.setCity("Wollongong");
        owner.setTelephone("4444444444");
        this.ownerService.saveOwner(owner);
        assertThat(owner.getId().longValue()).isNotEqualTo(0);

        owners = this.ownerService.findOwnerByLastName("Schultz");
        assertThat(owners.size()).isEqualTo(found + 1);
    }

    @Test
    public void updateOwnerTest() throws Exception {
        Owner owner = this.ownerService.findOwnerById(1);
        String lastName = owner.getLastName();
        owner.setLastName(lastName + "X");
        String firstName = owner.getFirstName();
        owner.setFirstName(firstName + "X");
        this.ownerService.saveOwner(owner);

        owner = this.ownerService.findOwnerById(1);
        assertThat(owner.getLastName()).isEqualTo(lastName + "X");
        assertThat(owner.getFirstName()).isEqualTo(firstName + "X");
    }

    @Test
    public void deleteOwnerTest() throws Exception {
        Owner owner = this.ownerService.findOwnerById(1);
        String lastName = owner.getLastName();
        List<Owner> owners = this.ownerService.findOwnerByLastName(lastName);
        int found = owners.size();

        this.ownerService.deleteOwner(owner);
        owners = this.ownerService.findOwnerByLastName(lastName);
        assertThat(owners.size()).isEqualTo(found - 1);
    }

}