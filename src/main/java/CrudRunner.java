
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.model.User;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
public class CrudRunner {
    
    public static void main( String[] args ) {
        create();
//        retrieve();
//        retrieveFromId(3);
//        updateAll();
//        deleteAll();
    }
    
    private static void create() {
        //creata configuration object 
        AnnotationConfiguration configuration = new AnnotationConfiguration();
        //make hibernate aware of User POJO
        configuration.addAnnotatedClass(User.class);
        
        //read and process hibernate.cfg.xml and JPA metadata
        SessionFactory factory = configuration.configure().buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        
        User user = new User();
        user.setPassword("abc2");
        session.saveOrUpdate(user);
        
        session.getTransaction().commit();
    }
    
    private static void retrieve() {
        //creata configuration object 
        AnnotationConfiguration configuration = new AnnotationConfiguration();
        //make hibernate aware of User POJO
        configuration.addAnnotatedClass(User.class);

        //read and process hibernate.cfg.xml and JPA metadata
        SessionFactory factory = configuration.configure().buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        List<User> allUsers;
        Query  queryResult = session.createQuery("from User");
        allUsers = queryResult.list();
        
        for (int i = 0; i < allUsers.size(); i++) {
            User user = allUsers.get(i);
            System.out.println(user);
        }

        session.getTransaction().commit();
    }
    
    private static User retrieveFromId(int id) {
        //creata configuration object 
        AnnotationConfiguration configuration = new AnnotationConfiguration();
        //make hibernate aware of User POJO
        configuration.addAnnotatedClass(User.class);

        //read and process hibernate.cfg.xml and JPA metadata
        SessionFactory factory = configuration.configure().buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        String queryString = "from User where id = :id";
        Query query = session.createQuery(queryString);
        query.setInteger("id", id);

        Object queryResult = query.uniqueResult();
        User user = (User)queryResult;

        session.getTransaction().commit();
        
        
        System.out.println(user);
        return user;
    }
    
    public static void updateAll() {
        //creata configuration object 
        AnnotationConfiguration configuration = new AnnotationConfiguration();
        //make hibernate aware of User POJO
        configuration.addAnnotatedClass(User.class);

        //read and process hibernate.cfg.xml and JPA metadata
        SessionFactory factory = configuration.configure().buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        List<User> allUsers;
        Query queryResult = session.createQuery("from User");
        allUsers = queryResult.list();
        
        for(User user : allUsers) {
            System.out.println(user);
            user.setPassword("password");
            session.update(user);
        }
        
        System.out.println("database updated");

        session.getTransaction().commit();
    }
    
    public static void deleteAll() {
        //creata configuration object 
        AnnotationConfiguration configuration = new AnnotationConfiguration();
        //make hibernate aware of User POJO
        configuration.addAnnotatedClass(User.class);

        //read and process hibernate.cfg.xml and JPA metadata
        SessionFactory factory = configuration.configure().buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        List<User> allUsers;
        Query queryResult = session.createQuery("from User");
        allUsers = queryResult.list();

        for (User user : allUsers) {
            session.delete(user);
        }

        session.getTransaction().commit();
    }
        
}
