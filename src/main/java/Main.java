
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
public class Main {
    
    private static final Logger logger = Logger.getLogger(Main.class);
    
    public static void main( String[] args ) {
        
//        HibernateUtil.recreateDatabase();
        logger.info("**********************************************************");
//        bySpring();
//        byHibernate();
//        hql();
        batch();
        namedQuery();
//        logger.info("**********************************************************");
    }
    
    private static void bySpring() {
        
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
        
        Session session = sessionFactory.getCurrentSession();
        
        
        session.beginTransaction();
        User user = new User();
        user.setPassword("abc2");
        session.saveOrUpdate(user);
        session.getTransaction().commit();
    }
    
    private static void byHibernate() {
//        //creata configuration object 
//        AnnotationConfiguration configuration = new AnnotationConfiguration();
//        //make hibernate aware of User POJO
//        configuration.addAnnotatedClass(User.class);
//        //read and process hibernate.cfg.xml and JPA metadata
//        configuration.configure();
//        //schemaExport call
//        new SchemaExport(configuration).create(true, true);
//        
//        SessionFactory factory = configuration.buildSessionFactory();
//        Session session = factory.getCurrentSession();
//        
//        session.beginTransaction();
//        User user = new User();
//        user.setPassword("abc1234");
//        user.setLoginName("ml");
//        user.setEncryptedPassword("asdf");
//        session.saveOrUpdate(user);
//        session.getTransaction().commit();
        
        //to samo przy użyciu HibernateUtil:
        Session s = HibernateUtil.getSession();
        try {
            s.beginTransaction();
            User u = new User();
            u.setPassword("asdfasdf");
            u.setEncryptedPassword("encrypted");
            u.setLoginName("ml");
            u.setEmailAddress("matik06@gmail.com");
            u.setLastAccessTime(new Date());
            u.setRegistrationDate(new GregorianCalendar());
            u.setVerified(false);
            u.setEmailAddress("email...");
            s.saveOrUpdate(u);
            HibernateUtil.commitTransaction();
        } catch (HibernateException e) {
            s.getTransaction().rollback();
            s.close();
            throw e;
        }
    }
    
    public static void hql() {
        Session session = HibernateUtil.beginTransaction();

        String query = "select loginName from User";
        List list = session.createQuery(query).list();
        
        for (int i = 0; i < list.size(); i++) {
            System.out.println((String)list.get(i));
        }
        
        query = "from User u where u.id = :id order by u.id ASC";
        User user = (User)session.createQuery(query)
                .setInteger("id", 5)
                .uniqueResult();
        System.out.println(user);
        
        query = "from User as u GROUP BY u.id HAVING u.id > 3";
        list = session.createQuery(query).list();
        for (int i = 0; i < list.size(); i++) {
            System.out.println((User) list.get(i));
        }
        
        HibernateUtil.commitTransaction();
    }
    
    //operacje grupowe
    public static void batch() {
        Session session = HibernateUtil.beginTransaction();

        String query = "update User set password = 'xxx' where password = 'asdfasdf'";
        int updated = session.createQuery(query).executeUpdate();
        System.out.println("updated: " + updated);
        
        query = "delete User where password = 'abc1234'";
        updated = session.createQuery(query).executeUpdate();
        System.out.println("deleted: " + updated);
        
        HibernateUtil.commitTransaction();
    }
    
    public static void namedQuery() {
        Session session = HibernateUtil.beginTransaction();

        int id = 5;
        Query query = session.getNamedQuery("findById");
        query.setInteger("id", 5);
        User user = (User)query.uniqueResult();
        System.out.println(user);

        HibernateUtil.commitTransaction();
    }
}

/**
 * Session.flush() - powoduje zapisanie obiektów w sesji do bazy danych
 *      AUTO - session is typically flushed before query execution to ensure query result do not contain stale data
 *      ALWAYS - the Session is flushed before every query
 *      COMMIT - the Session is flushed when the transation.commit() method is called
 *      MANUAL - the session is only flushed when the flush() method is invoked on the Session
 * 
 * Session.refresh(Object o) - przeciwieństwo flush, 
 * Session.evict(Object o) - odłączenie obiektu od sesji (obiekt przestaje być śledzony przez hibernata) - zmiany na obiekcie nie zapisują się do bazy od momentu wywołania metody
 * 
 * NonUniqueObjectException - wyjątek wyrzucany w sytuacji gdy w tej samej sesji mamy dwa obiekty o tym samym id
 */
