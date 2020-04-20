package be.fastned.application;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@ComponentScan("be.fastned.application")
@EnableTransactionManagement
public class AppConfig {

    // Constanten
    public static final String BEAN_DATASOURCE = "datasource";
    public static final String BEAN_SESSIONFACTORY = "sessionFactory";
    public static final String BEAN_TRANSACTIONMANAGER = "transactionManager";
    public static final String BEAN_PERSISTANCESERVICE = "persistenceService";

    // Persistentie
    @Bean
    public BasicDataSource datasource(){
        try{
            BasicDataSource ds = new BasicDataSource();
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setUrl("jdbc:mysql://localhost:3306/dbfastned");
            ds.setUsername("fastnedApp");
            ds.setPassword("{fastnedApp --> <pwd> (!ïr0nçlad$tróng!) <pwd/>}");
            ds.setInitialSize(5);
            ds.setMaxActive(10);
            ds.setDefaultTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);

            System.out.println("[!!!]  Bean datasource is aangemaakt!");
            return ds;
        }
        catch(Exception ex){
            System.out.println("[!!!]  Bean datasource is niet aangemaakt met exception: " + ex.getMessage());
        }
        return null;
    }

    // HV 20150210 - Aangepast aan Hibernate 4
    @Bean(name = BEAN_SESSIONFACTORY, destroyMethod="close")
    public SessionFactory sessionFactory() throws Exception{
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(datasource());
        sf.setPackagesToScan(new String[]{"be.fastned.application.code.domain"});
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
        sf.setHibernateProperties(hibernateProperties);
        sf.afterPropertiesSet();

        System.out.println("[!!!]  Bean sessionFactory is aangemaakt!");
        return sf.getObject();
    }

    @Bean
    public HibernateTransactionManager transactionManager() throws Exception{
        try{
            HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(sessionFactory());
            System.out.println("[!!!]  Bean transactionManager is aangemaakt!");
            return hibernateTransactionManager;
        }
        catch(Exception ex){
            System.out.println("[!!!]  Bean transactionManager is niet aangemaakt met exception: " + ex.getMessage());
        }
        return null;
    }

//    @Bean
//    public PersistenceService persistenceService() {
//        try{
//            PersistenceService persistenceService = new PersistenceServiceImpl();
//            System.out.println("Bean persistenceService is aangemaakt!");
//            return persistenceService;
//        }
//        catch(Exception ex){
//            System.out.println("Bean persistenceService is niet aangemaakt met exception: " + ex.getMessage());
//        }
//        return null;
//    }

    /*@Bean
    Persoon actieveGebruiker(){
        return ((Persoon)new Planner("test@email.com", "testman", "XXX"));
//        return new Laadklant("test@email.com", "testman", "XXX");
//        return new Installateur("test@email.com", "testman", "XXX");
//        return new Locatiehouder("test@email.com", "testman", "XXX");
    }*/
}
