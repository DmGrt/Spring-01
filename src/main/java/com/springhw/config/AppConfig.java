package com.springhw.config;

import com.springhw.models.User;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = {
        "com.springhw.service",
        "com.springhw.dao"
})
public class AppConfig {
    private final Environment environment;

    public AppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driver"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.user"));
        dataSource.setPassword(environment.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean =
                new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.generate_statistics",
                environment.getProperty("hibernate.generate_statistics"));
        properties.put("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));

        factoryBean.setHibernateProperties(properties);
        factoryBean.setAnnotatedClasses(User.class);
        return factoryBean;
    }
}
