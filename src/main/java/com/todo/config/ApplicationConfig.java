package com.todo.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.todo.repository.TodoRepository;

@Configuration
@EnableJpaRepositories(basePackageClasses=TodoRepository.class,entityManagerFactoryRef="entityManagerFactory",transactionManagerRef="transactionManager")
public class ApplicationConfig{

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPersistenceUnitName("todo-pu");
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setShowSql(true);
		entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
		return entityManagerFactory;
	}

//	@Bean(destroyMethod = "close")
//	public DataSource dataSource() {
//		String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
//		String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
//		String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
//		String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
//        String databaseName = System.getenv("OPENSHIFT_APP_NAME");
//		String url = "jdbc:mysql://" + host + ":" + port + "/"+databaseName;
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUrl(url);
//		dataSource.setUsername(username);
//		dataSource.setPassword(password);
//		dataSource.setTestOnBorrow(true);
//		dataSource.setTestOnReturn(true);
//		dataSource.setTestWhileIdle(true);
//		dataSource.setTimeBetweenEvictionRunsMillis(1800000);
//		dataSource.setNumTestsPerEvictionRun(3);
//		dataSource.setMinEvictableIdleTimeMillis(1800000);
//		dataSource.setValidationQuery("SELECT version()");
//
//		return dataSource;
//
//	}
//	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		String username = "root";
		String password = "";
		String host = "localhost";
		String port = "3306";
        String databaseName = "todoz";
		String url = "jdbc:mysql://" + host + ":" + port + "/"+databaseName;
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setTestOnBorrow(true);
		dataSource.setTestOnReturn(true);
		dataSource.setTestWhileIdle(true);
		dataSource.setTimeBetweenEvictionRunsMillis(1800000);
		dataSource.setNumTestsPerEvictionRun(3);
		dataSource.setMinEvictableIdleTimeMillis(1800000);
		dataSource.setValidationQuery("SELECT version()");

		return dataSource;
	}
	
	@Bean
	@Autowired 
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		return jpaTransactionManager;
	}
	
}
