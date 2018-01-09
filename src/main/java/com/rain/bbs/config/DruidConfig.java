package com.rain.bbs.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class DruidConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.type}")
    private String dataSourceType;

    @Value("${spring.datasource.tomcat.initial-size}")
    private int initialSize;

    @Value("${spring.datasource.tomcat.max-active}")
    private int maxActive;

    @Value("${spring.datasource.tomcat.min-idle}")
    private int minIdle;

    @Value("${spring.datasource.tomcat.max-wait}")
    private int maxWait;

    @Value("${spring.datasource.tomcat.time-between-eviction-runs-millis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.tomcat.min-evictable-idle-time-millis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.tomcat.validation-query}")
    private String validationQuery;

    @Value("${spring.datasource.tomcat.test-on-borrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.tomcat.test-on-return}")
    private boolean testOnReturn;

    @Value("${spring.datasource.tomcat.test-while-idle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.filters}")
    private String filters;


    @Bean(name = "dataSource")
    public DruidDataSource druidDataSource(){
        DruidDataSource dataSource = new DruidDataSource();

        try {
            //dataSource.setDbType(dataSourceType);
            dataSource.setUrl(url);
            dataSource.setDriverClassName(driverClass);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            dataSource.setTimeBetweenConnectErrorMillis(timeBetweenEvictionRunsMillis);
            dataSource.setMaxActive(maxActive);
            dataSource.setMaxWait(maxWait);
            dataSource.setMinIdle(minIdle);
            dataSource.setValidationQuery(validationQuery);
            dataSource.setTestOnBorrow(testOnBorrow);
            dataSource.setTestOnReturn(testOnReturn);
            dataSource.setTestWhileIdle(testWhileIdle);
            dataSource.setFilters(filters);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public ServletRegistrationBean druidServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean();
        bean.setServlet(new StatViewServlet());
        bean.addUrlMappings("/druid2/*");
        bean.addInitParameter("loginUsername","susan2");
        bean.addInitParameter("loginPassword","123456");
        return bean;
    }

    @Bean
    public FilterRegistrationBean druidFilter(){
        FilterRegistrationBean bean  = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        bean.addUrlPatterns("/*");
        bean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        bean.addInitParameter("profileEnable", "true");
        return bean;
    }
}
