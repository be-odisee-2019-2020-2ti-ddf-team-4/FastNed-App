package be.fastned.application.service;

import be.fastned.application.domain.Installateur;
import be.fastned.application.domain.Laadklant;
import be.fastned.application.domain.Locatiehouder;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@ComponentScan("be.fastned.application")
@EnableTransactionManagement
//@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public BasicDataSource datasource(){
        try{
            BasicDataSource ds = new BasicDataSource();
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setUrl("jdbc:mysql://localhost:3306/ddfbank");
            ds.setUsername("ddf");
            ds.setPassword("ddfpw");
            ds.setInitialSize(5);
            ds.setMaxActive(10);
            ds.setDefaultTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);

            System.out.println("Bean datasource is aangemaakt!");
            return ds;
        }
        catch(Exception ex){
            System.out.println("Bean datasource is niet aangemaakt met exception: " + ex.getMessage());
        }
        return null;
    }

    // HV 20150210 - Aangepast aan Hibernate 4
    @Bean (destroyMethod="close")
    public SessionFactory sessionFactory() throws Exception{
        try{
            LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
            sf.setDataSource(datasource());
            sf.setPackagesToScan(new String[]{"be.hubrussel.domain"});
            Properties hibernateProperties = new Properties();
            hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
            sf.setHibernateProperties(hibernateProperties);
            sf.afterPropertiesSet();

            System.out.println("Bean sessionFactory is aangemaakt!");
            return (SessionFactory) sf.getObject();
        }
        catch(Exception ex){
            System.out.println("Bean sessionFactory is niet aangemaakt met exception: " + ex.getMessage());
        }
        return null;
    }

    @Bean
    public HibernateTransactionManager transactionManager() throws Exception{
        try{
            HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(sessionFactory());
            System.out.println("Bean transactionManager is aangemaakt!");
            return hibernateTransactionManager;
        }
        catch(Exception ex){
            System.out.println("Bean transactionManager is niet aangemaakt met exception: " + ex.getMessage());
        }
        return null;
    }

    @Bean
    public Laadklant activeGebruikerInst() {
        try{
            Laadklant laadklant = new Laadklant(
                    "bannerbanner", "ww6", "Banner", "Bruce", "man",
                    "brucy.banny@gmail.com", "0497618196"
            );
            System.out.println("Bean activeGebruikerInst is aangemaakt!");
            return laadklant;
        }
        catch(Exception ex){
            System.out.println("Bean activeGebruikerInst is niet aangemaakt met exception: " + ex.getMessage());
        }
        return null;
    }

    @Bean
    public Laadklant testLaadklant() {
        try{
            Laadklant laadklant = new Laadklant(
                    "bannerbanner", "ww6", "Banner", "Bruce", "man",
                    "brucy.banny@gmail.com", "0497618196"
            );
            System.out.println("Bean testLaadklant is aangemaakt!");
            return laadklant;
        }
        catch(Exception ex){
            System.out.println("Bean testLaadklant is niet aangemaakt met exception: " + ex.getMessage());
        }
        return null;
    }
    @Bean
    public Locatiehouder testLocatiehouder() {
        try{
            Locatiehouder locatiehouder = new Locatiehouder(
                    "johnjohn", "ww6", "Constantine", "John", "man",
                    "john.const@gmail.com", "0497618192", "Constantine Inc.",
                    "bosweg 182 9340 LEDE België", "BE07 255 355 366"
            );
            System.out.println("Bean testLocatiehouder is aangemaakt!");
            return locatiehouder;
        }
        catch(Exception ex){
            System.out.println("Bean testLocatiehouder is niet aangemaakt met exception: " + ex.getMessage());
        }
        return null;
    }
    @Bean
    public Installateur testInstallateur() {
        try{
            Installateur installateur = new Installateur(
                    "queenElizabeth", "ww6", "Elizabeth", "Queen", "vrouw",
                    "queen.elizzy@gmail.com", "0497618199"
            );
            System.out.println("Bean testInstallateur is aangemaakt!");
            return installateur;
        }
        catch(Exception ex){
            System.out.println("Bean testInstallateur is niet aangemaakt met exception: " + ex.getMessage());
        }
        return null;
    }
    @Bean
    public PersistenceService persistenceService() {
        try{
            PersistenceService persistenceService = new PersistenceServiceImpl();
            System.out.println("Bean persistenceService is aangemaakt!");
            return persistenceService;
        }
        catch(Exception ex){
            System.out.println("Bean persistenceService is niet aangemaakt met exception: " + ex.getMessage());
        }
        return null;
    }
}
