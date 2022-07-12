package com.example.assignment4.config;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Getter
@Setter
public class HibernateConfig {

    @Autowired
    private HibernateDbProperties hibernateDbProperties;

    @Bean
    public DataSource dataSource(){
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(hibernateDbProperties.getUrl());
        dataSource.setDriverClassName(hibernateDbProperties.getDriver());
        dataSource.setUsername(hibernateDbProperties.getUsername());
        dataSource.setPassword(hibernateDbProperties.getPassword());
        return dataSource;
    }

//    @Bean
//    public EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties ){
//        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("com/example/assignment");
//        em.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
//        em.setJpaProperties( hibernateProperties );
//        em.setPersistenceUnitName( "demo-unit" );
//        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
//        em.afterPropertiesSet();
//        return em.getObject();
//    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager =
                new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private final Properties createHibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.show_sql", hibernateDbProperties.getShowSql());
        hibernateProperties.setProperty("hibernate.dialect", hibernateDbProperties.getDialect());

        return hibernateProperties;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.example.assignment4.entity");
        sessionFactory.setHibernateProperties(createHibernateProperties());

        return sessionFactory;
    }

}
