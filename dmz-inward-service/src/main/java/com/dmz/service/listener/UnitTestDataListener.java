package com.dmz.service.listener;

import com.dmz.service.annotation.DataPrepare;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by dmz on 2016/12/8.
 */
public class UnitTestDataListener extends AbstractTestExecutionListener {

    private final static Logger LOG = LoggerFactory.getLogger(UnitTestDataListener.class);

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        super.beforeTestMethod(testContext);

        ApplicationContext applicationContext = testContext.getApplicationContext();
        Class<?> testClass = testContext.getTestClass();
        Method testMethod = testContext.getTestMethod();

        if (testMethod.isAnnotationPresent(DataPrepare.class)) {
            handleAnnotation(applicationContext, testMethod.getAnnotation(DataPrepare.class));
        } else if (testClass.isAnnotationPresent(DataPrepare.class)) {
            handleAnnotation(applicationContext, testClass.getAnnotation(DataPrepare.class));
        }

    }

    private void handleAnnotation(ApplicationContext applicationContext, DataPrepare dataPrepare) throws Exception {

        DataSource dataSource = (DataSource) applicationContext.getBean(dataPrepare.dataSource());
        Connection connection = dataSource.getConnection();

        String[] dataLocations = dataPrepare.setDataLocations();
        for (String dataLocation : dataLocations) {
            Resource resource = applicationContext.getResource(dataLocation);
            ScriptUtils.executeSqlScript(connection, new EncodedResource(resource, "utf8"));
        }
        String sql = dataPrepare.setDataSql();
        if (sql != null && sql.length() > 0) {
            Statement stm = connection.createStatement();
            int result = stm.executeUpdate(sql);
            LOG.info("Executed Sql:{},Result is {}", sql, result);
            stm.close();
        }

    }
}
