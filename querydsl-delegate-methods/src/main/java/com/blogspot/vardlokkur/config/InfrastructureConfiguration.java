package com.blogspot.vardlokkur.config;

import com.mysema.query.jpa.HQLTemplates;
import com.mysema.query.jpa.JPQLTemplates;
import org.hibernate.cfg.AvailableSettings;
import org.hsqldb.jdbc.JDBCDriver;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import java.util.Properties;

/**
 * Provides infrastructure configuration.
 *
 * @author Warlock
 * @since 1.0
 */
@Configuration
@ComponentScan("com.blogspot.vardlokkur.domain")
@EnableJpaRepositories(
    basePackages = {"com.blogspot.vardlokkur.domain"},
    repositoryImplementationPostfix = "Precursor")
@EnableTransactionManagement
public class InfrastructureConfiguration implements TransactionManagementConfigurer {

    private static final String DRIVER_CLASS = JDBCDriver.class.getName();

    /**
     * {@inheritDoc}
     */
    @Bean(name = "transactionManager")
    @Override
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        try {
            return new JpaTransactionManager(entityManagerFactory().getObject());
        } catch (final Exception exception) {
            throw new BeanCreationException("Unable to create Platform Transaction Manager.", exception);
        }
    }

    @Bean(destroyMethod = "shutdown")
    protected EmbeddedDatabase dataSource() {
        return new EmbeddedDatabaseBuilder().setName("Embedded DB")
                                            .setType(EmbeddedDatabaseType.HSQL)
                                            .addScripts("reload-scheme.sql", "test-data.sql")
                                            .build();
    }

    @Bean
    protected AbstractEntityManagerFactoryBean entityManagerFactory() {

        // Prepare Hibernate properties first, ...
        final Properties properties = new Properties();
        properties.setProperty(AvailableSettings.AUTOCOMMIT, "false");
        properties.setProperty(AvailableSettings.DRIVER, DRIVER_CLASS);

        // ... create, initialize and return appropriate factory bean.
        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        factory.setJpaProperties(properties);
        return factory;
    }

    @Bean
    protected JpaVendorAdapter jpaVendorAdapter() {
        final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.HSQL);
        adapter.setGenerateDdl(false);
        adapter.setShowSql(true);
        return adapter;
    }

    @Bean
    protected JPQLTemplates jpqlTemplates() {
        return HQLTemplates.DEFAULT;
    }

}
