package me.j360.mysql.proxy;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.Getter;

import javax.sql.DataSource;
import java.util.Properties;


@Getter
public final class DataSourceManager {
    
    @Getter
    private static DataSourceManager instance = new DataSourceManager();
    
    private final DataSource dataSource;
    
    public DataSourceManager() {
        Properties p = new Properties();
        try {
            p.setProperty("url","jdbc:mysql://localhost:3306/us2?setUnicode=true&amp;characterEncoding=UTF8");
            p.setProperty("username","root");
            p.setProperty("password","root");
            dataSource = DruidDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


}
