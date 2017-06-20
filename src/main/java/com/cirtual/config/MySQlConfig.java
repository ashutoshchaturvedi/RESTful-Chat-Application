/*
package com.cirtual.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.transaction.PlatformTransactionManagerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;


@Configuration
@Import(RepositoryRestMvcConfiguration.class)
@EnableJpaRepositories(entityManagerFactoryRef = "mysqlEntityManagerFactory" , transactionManagerRef = "mysqlTransactionManagerFactory")
public class MySQlConfig {

    String connectionURL;

    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }


    PlatformTransactionManager myslPlatformTransactionManager () {

        EntityManagerFactory entityManagerFactory = mysqlEntityManagerFactory().getObject();
        return  new JpaTransactionManager(entityManagerFactory);
    }

    LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setGenerateDdl(false);
        DataSource dataSource = dataSource();
        setConnectionURL(dataSource);
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("com.cirtual.entity");
        factoryBean.setPersistenceUnitName("mysql");
        return factoryBean;

    }

    private Properties hibernateProperties() {
        return new Properties(){
            private static  final long serialVersionUID = 1L;
            {
                setProperty("hibernate.hbm2ddl.auto", "none");
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            }
        };

    }

    public void setConnectionURL(DataSource dataSource){
        try {
            connectionURL = dataSource.getConnection().getMetaData().getURL().toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




To do


--Import as Maven Project

--Buid Application

Configure MySQL in application.properties


Create below table

CREATE DATABASE chatApplication;

use chatApplication;

CREATE TABLE User (
    id int NOT NULL,
    firstName varchar(255) NOT NULL,
    lastName varchar(255),
    age  int,
	profession varchar(20)
);

ALTER TABLE User ADD PRIMARY KEY(id);



Run Application.java 

curl -u user:password -i -X GET 'Accept:appliction/json' http://localhost:8080/users

curl -u user:password -i -X POST -H "Content-Type:application/json" -d "{  \"firstName\" : \"Abhay\",  \"lastName\" : \"Chaubey\", \"age\" : \"29\", \"profession\" : \"SW\" }" http://localhost:8080/users/

curl -u user:password -i -X POST -H "Content-Type:application/json" -d "{  \"firstName\" : \"Ashu\",  \"lastName\" : \"Chaubey\", \"age\" : \"29\", \"profession\" : \"SWD\" }" http://localhost:8080/users/


curl -u user:password -i -X GET 'Accept:appliction/json' http://localhost:8080/users/search/findByfirstName?name=Ashu


*/
