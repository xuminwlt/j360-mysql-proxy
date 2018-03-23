package me.j360.mysql.proxy.test;

import me.j360.mysql.proxy.DataSourceManager;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

/**
 * @author: min_xu
 * @date: 2018/3/23 下午6:13
 * 说明：
 */


public class DatasourceTest {

    @Test
    public void druidTest() throws SQLException {
        DataSourceManager dataSourceManager = new DataSourceManager();

        Assert.assertNotNull(dataSourceManager.getDataSource().getConnection());
    }

}
