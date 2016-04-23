package org.zbo.done.util;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.zbo.done.AbstractTestBase;

import static org.junit.Assert.*;

/**
 * Created by zbo on 4/23/16.
 */
public class JsonUtilTests extends AbstractTestBase{

    @Test
    public void toJsonTest() throws Exception {
        new JsonUtil();
        Assert.assertEquals(JsonUtil.toJson(null),"null");
    }

}