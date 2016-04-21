package org.zbo.done;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
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

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by zbo on 4/19/16.
 */
@ContextConfiguration(locations = {"classpath:spring/business-config.xml", "classpath:spring/mvc-core-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractTestBase {
    @BeforeClass
    public static void resetData(){
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//        populator.addScript(new ClassPathResource("db/mysql/initDB.sql"));
//        populator.addScript(new ClassPathResource("db/mysql/populateDB.sql"));
        populator.addScript(new ClassPathResource("db/hsqldb/initDB.sql"));
        populator.addScript(new ClassPathResource("db/hsqldb/populateDB.sql"));

        Connection connection = null;
        Resource res = new ClassPathResource("spring/business-config.xml");
        DefaultListableBeanFactory factory= new DefaultListableBeanFactory ();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(res);

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/business-config.xml");
        DataSource bean = context.getBean("dataSource", DataSource.class);

        DataSource dataSource = factory.getBean("dataSource", DataSource.class);
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
