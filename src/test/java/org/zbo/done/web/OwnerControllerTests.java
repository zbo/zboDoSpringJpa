package org.zbo.done.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.zbo.done.AbstractTestBase;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Created by zbo on 4/21/16.
 */

public class OwnerControllerTests extends AbstractTestBase {

    private static final int TEST_OWNER_ID = 1;

    @Autowired
    private OwnerController ownerController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    public void showOwnerTest() throws Exception {
        ResultActions actions = mockMvc.perform(get("/owners/{ownerId}", TEST_OWNER_ID))
                .andExpect(status().isOk());
        actions.andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.telephone").value("6085551023"));
    }

}