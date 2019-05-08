package com.lalsberg.ops.script;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class MarketplaceDatabaseConfig {

	@Bean(name = "marketplaceDataSourceProperties")
    @ConfigurationProperties("marketplace.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

	@Bean(name = "marketplaceDataSource")
	@ConfigurationProperties(prefix = "marketplace.datasource")
	public DataSource dataSource(@Qualifier("marketplaceDataSourceProperties") DataSourceProperties properties) {
		return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean(name = "marketplaceEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean marketplaceEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("marketplaceDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.lalsberg.script.bla").persistenceUnit("marketplace").build();
	}

	@Bean(name = "marketplaceTransactionManager")
	public PlatformTransactionManager marketplaceTransactionManager(@Qualifier("marketplaceEntityManagerFactory") EntityManagerFactory marketplaceEntityManagerFactory) {
		return new JpaTransactionManager(marketplaceEntityManagerFactory);
	}

}
