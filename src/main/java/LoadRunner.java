
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.model.User;
import org.hibernate.model.User;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
public class LoadRunner {
    
    public static void main( String[] args ) {
        callGet();
        callLoad();
    }
    
    //wczytuje obiekt z bazy dopiero w momencie gdy próbujemy odczytać pole obiektu się do zmiennej obiektu
    //jeśli ma to miejsce poza sesją to otrzymujemy LazyInitializationException
    //w przypadku wczytania nieistniejącego obiektu z bazy przy próbie odczytu pól klasy dostajemy ObjectNotFoundException
    //zapewnia, że obiekt zostanie wczytany z bazy danych a nie z jakiejś sesji czy JVM
    public static void callLoad() {
        AnnotationConfiguration configuration = new AnnotationConfiguration();
        configuration.addAnnotatedClass(User.class);
        SessionFactory factory = configuration.configure().buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        User user = (User) session.load(User.class, new Long(5));
        System.out.println(user);
        
        session.getTransaction().commit();
        
    }
    
    //zapewnia wczytanie obiektu z bazy
    public static void callGet() {
        AnnotationConfiguration configuration = new AnnotationConfiguration();
        configuration.addAnnotatedClass(User.class);
        SessionFactory factory = configuration.configure().buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, new Long(1));

        session.getTransaction().commit();
        System.out.println(user);
    }
}
