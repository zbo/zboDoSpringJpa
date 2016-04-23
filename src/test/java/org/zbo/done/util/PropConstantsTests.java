package org.zbo.done.util;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.zbo.done.AbstractTestBase;

import static org.junit.Assert.*;

/**
 * Created by zbo on 4/23/16.
 */
public class PropConstantsTests extends AbstractTestBase {
    @Autowired
    PropConstants constants;
    @Test
    public void getPropertiesTest(){
        constants.getInitLocation().startsWith("jdbc");
        constants.getDataLocation().startsWith("jdbc");
    }
}