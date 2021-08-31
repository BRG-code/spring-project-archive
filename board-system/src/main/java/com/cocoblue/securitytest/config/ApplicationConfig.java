package com.cocoblue.securitytest.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.cocoblue.securitytest.dao", "com.cocoblue.securitytest.service"})
@EnableTransactionManagement
public class ApplicationConfig implements TransactionManagementConfigurer {
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DbConfig.DRIVERCLASSNAME);
        dataSource.setUrl(DbConfig.URL);
        dataSource.setUsername(DbConfig.USERNAME);
        dataSource.setPassword(DbConfig.PASSWORD);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }
}