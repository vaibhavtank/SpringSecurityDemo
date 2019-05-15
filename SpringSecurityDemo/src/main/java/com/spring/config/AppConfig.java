package com.spring.config;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.spring" })
@EnableTransactionManagement
@Import({ SecurityConfig.class })
public class AppConfig extends WebMvcConfigurerAdapter {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	/**
	 * @return SessionFactory
	 */
	@Autowired
	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
		builder.scanPackages("com.spring.core.model").scanPackages("com.spring.restmodel").addProperties(getHibernateProperties());
		return builder.buildSessionFactory();
	}

	/**
	 * @return Properties
	 */
	private Properties getHibernateProperties() {
		Properties prop = new Properties();
		prop.put("hibernate.format_sql", "true");
		prop.put("hibernate.show_sql", "true");
		prop.put("hibernate.hbm2ddl.auto", "update");
		prop.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		prop.put("hibernate.connection.pool_size", "20");
		return prop;
	}

	/**
	 * @return BasicDataSource
	 */
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		boolean remote = false;
		String DBNAME = ((remote) ? "emxcelso_desk_db" : "desk_db");
		String USERNAME = ((remote) ? "emxcelso_desk_db" : "root");
		String PASSWORD = ((remote) ? "xF2yXoQf" : "12345");
		ds.setUrl("jdbc:mysql://localhost:3306/" + DBNAME);
		ds.setUsername(USERNAME);
		ds.setPassword(PASSWORD);
		return ds;
	}

	/**
	 * @param s
	 * @return HibernateTransactionManager
	 */
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}

	/**
	 * @return InternalResourceViewResolver
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}