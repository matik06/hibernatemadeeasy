
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.model.User;
import org.hibernate.tool.hbm2ddl.SchemaExport;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Singleton
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
public class HibernateUtil {
    
    private static SessionFactory facotry;
    
    public static Session getSession() {
        if (facotry == null) {
            Configuration config = HibernateUtil.getInitializedConfiguration();
            facotry = config.buildSessionFactory();
        }
        
        Session hibernateSession = facotry.getCurrentSession();
        
        return hibernateSession;
    }
    
    private static Configuration getInitializedConfiguration() {
        AnnotationConfiguration configuration = new AnnotationConfiguration();
        configuration.addAnnotatedClass(User.class);
        configuration.configure();
                
        return configuration;
    }
    
    //restart bazy danych
    public static void recreateDatabase() {
        Configuration config;
        config = getInitializedConfiguration();
        new SchemaExport(config).create(true, true);
    }
    
    public static Session beginTransaction() {
        Session hibernateSession;
        hibernateSession = HibernateUtil.getSession();
        hibernateSession.beginTransaction();
        
        return hibernateSession;
    }
    
    public static void commitTransaction() {
        HibernateUtil.getSession().getTransaction().commit();
    }
    
    public static void closeSession() {
        HibernateUtil.getSession().close();
    }
    
    public static void rollbackTransaction() {
        HibernateUtil.getSession().getTransaction().rollback();
    }
}
