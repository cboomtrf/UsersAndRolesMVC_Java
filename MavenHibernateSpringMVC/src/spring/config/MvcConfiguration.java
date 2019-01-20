package spring.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * NOTE: CHECK POM CONFIG FOR HIBERNATE VERSION AND UPDATE IMPORTS IF NEEDED FOR HIBERNATE 5.
 * @author CBoom (cboom.trf@gmail.com)
 *
 */
@Configuration
@ComponentScan("spring")
@EnableWebMvc
@EnableTransactionManagement //is necessary for sql.
public class MvcConfiguration extends WebMvcConfigurerAdapter {
	
	private String dbName = "test";
	private String dbDriver = "com.mysql.jdbc.Driver";
	private String dbConnectionUrl = "jdbc:mysql://localhost/" + dbName; 
	private String dbUser = "root"; 
	private String dbPassword = ""; 
	
    private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static final String HIBERNATE_SHOW_SQL = "true";
    private static final String HIBERNATE_HBM2DDL_AUTO = "update"; //create or update
    private static final String ENTITYMANAGER_PACKAGES_TO_SCAN = "spring.model";
	
    private static final String HIBERNATE_DIALECT_PROPERTY = "hibernate.dialect";
    private static final String HIBERNATE_SHOW_SQL_PROPERTY = "hibernate.show_sql";
    private static final String HIBERNATE_HBM2DDL_AUTO_PROPERTY = "hibernate.hbm2ddl.auto";

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(dbConnectionUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }
    
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
        sessionFactoryBean.setHibernateProperties(hibProperties());
        return sessionFactoryBean;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(HIBERNATE_DIALECT_PROPERTY, HIBERNATE_DIALECT);
        properties.put(HIBERNATE_SHOW_SQL_PROPERTY, HIBERNATE_SHOW_SQL);
        properties.put(HIBERNATE_HBM2DDL_AUTO_PROPERTY, HIBERNATE_HBM2DDL_AUTO);
        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
