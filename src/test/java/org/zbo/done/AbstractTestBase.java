package org.zbo.done;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zbo.done.service.OwnerService;
import org.zbo.done.util.DBInitUtil;
import org.zbo.done.util.PropConstants;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by zbo on 4/19/16.
 */
@ContextConfiguration(locations = {"classpath:spring/business-config.xml", "classpath:spring/mvc-core-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractTestBase {

    @BeforeClass
    public static void resetData() throws IOException {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        Connection connection = null;
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/business-config.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        DBInitUtil dbInitUtil = context.getBean("dbInitUtil", DBInitUtil.class);
        populator.addScript(new ClassPathResource(dbInitUtil.getInitLocation()));
        populator.addScript(new ClassPathResource(dbInitUtil.getDataLocation()));
        populator.addScript(new ClassPathResource(dbInitUtil.getSecurityLocation()));

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            populator.populate(connection);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
    }
}
