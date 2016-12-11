package com.scrapnews.config;

import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan({"com.scrapnews.*" })
@EnableTransactionManagement
@Import({ SecurityConfig.class })
public class AppConfig {

	@Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder
        	.scanPackages("com.scrapnews.model.businessobjects")
            .addProperties(getHibernateProperties());

        return builder.buildSessionFactory();
    }

	private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return prop;
    }
	
	
	// aws cloud database source
	@Bean(name = "dataSource")
	public BasicDataSource dataSource() {
		
		BasicDataSource ds = new BasicDataSource();
	    ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://aapq21lfjp51ba.cpjoj8sdntqf.us-west-2.rds.amazonaws.com:3306/scrapnews_db");
		ds.setUsername("scrapnews514");
		ds.setPassword("514Montreal");
		return ds;
	}
	
	@Bean
    public HibernateTransactionManager txManager() {
        return new HibernateTransactionManager(sessionFactory());
    }
	
}