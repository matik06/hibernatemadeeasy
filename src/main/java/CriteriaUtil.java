
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.model.User;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
public class CriteriaUtil {
    
    private static final Logger logger = Logger.getLogger(CriteriaUtil.class);
    
    public static void main(String[] args) {
//        HibernateUtil.recreateDatabase();
        logger.info("**********************************************************");
//        example1();
//        example2();
//        example3();
//        example4();
//        exampleRexp();
//        exampleIgnoreCase();
//        exampleFindAll();
//        exampleLimitResult();
//        orderBy();
//        restrictions();
//        creterions();
        List<User> users = findByCriterion(
                Restrictions.gt("id", 2L),
                Restrictions.lt("id", 5L),
                Restrictions.isNotNull("emailAddress"));
        for(User user : users) {
            System.out.println(user);
        }
        logger.info("**********************************************************");
    }    
    
    //wyszukiwanie po 1 kolumnie
    public static void example1() {
        Session session = HibernateUtil.beginTransaction();

        User user = new User();
        user.setVerified(false);        
        
        Example example = Example.create(user);
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(example);        
        List<User> users = criteria.list();        

        HibernateUtil.commitTransaction();
        
        for(User u : users) {
            System.out.println(u);
        }
    }
    
    //wyszukiwanie po dwóch kolumnach
    public static void example2() {
        Session session = HibernateUtil.beginTransaction();

        User user = new User();
        user.setVerified(false);
        user.setRegistrationDate(new GregorianCalendar());

        Example example = Example.create(user);
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(example);
        List<User> users = criteria.list();

        HibernateUtil.commitTransaction();

        for (User u : users) {
            System.out.println(u);
        }
    }
    
    //zwracanie pojedyńczego wyniku
    public static void example3() {
        Session session = HibernateUtil.beginTransaction();

        User user = new User();
        user.setLoginName("mj");
        user.setPassword("abc123");

        Example example = Example.create(user);
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(example);
        User result = (User)criteria.uniqueResult();

        HibernateUtil.commitTransaction();

        System.out.println(result);        
    }
    
    //przykład pomijania niktórych pól w wynikowym zapytaniu
    public static void example4() {
        Session session = HibernateUtil.beginTransaction();

        User user = new User();
        user.setLoginName("mj");
        user.setPassword("abc123");
        
        Example example = Example.create(user);
        //pominięcie pól których wartość = 0
        example.excludeZeroes();
        //zawarcie w zapytaniu wartości których wartość = null oraz = 0
        //domyślnie 0 jest brane pod uwagę; null nie jest brane
        example.excludeNone();
        //pominięcie wybranego pola
        example.excludeProperty("verified");
        
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(example);
        User result = (User) criteria.uniqueResult();

        HibernateUtil.commitTransaction();

        System.out.println(result);
    }
    
     //przykład zapytania z LIKE
    public static void exampleRexp() {
        Session session = HibernateUtil.beginTransaction();

        User user = new User();
        user.setEmailAddress(".com");
        
        Example example = Example.create(user);
        example.enableLike(MatchMode.END);
        
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(example);
        List<User> result = criteria.list();

        HibernateUtil.commitTransaction();

        for (User u : result) {
            System.out.println(u);
        }
    }
    
    public static void exampleIgnoreCase() {
        Session session = HibernateUtil.beginTransaction();

        User user = new User();
        user.setPassword("PASS");

        Example example = Example.create(user);
        example.ignoreCase();
        example.enableLike(MatchMode.ANYWHERE);

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(example);
        List<User> result = criteria.list();

        HibernateUtil.commitTransaction();

        for (User u : result) {
            System.out.println(u);
        }
    }
    
    public static void exampleFindAll() {
        Session session = HibernateUtil.beginTransaction();

        User user = new User();

        Example example = Example.create(user);
        Criteria criteria = session.createCriteria(User.class);
        List<User> result = criteria.list();

        HibernateUtil.commitTransaction();

        for (User u : result) {
            System.out.println(u);
        }
    }
    
    public static void exampleLimitResult() {
        Session session = HibernateUtil.beginTransaction();

        User user = new User();

        Example example = Example.create(user);
        Criteria criteria = session.createCriteria(User.class);
        criteria.setFirstResult(0);
        criteria.setMaxResults(5);
        
        List<User> result = criteria.list();

        HibernateUtil.commitTransaction();

        for (User u : result) {
            System.out.println(u);
        }
    }
    
    public static void orderBy() {
        Session session = HibernateUtil.beginTransaction();

        User user = new User();

        Example example = Example.create(user);
        Criteria criteria = session.createCriteria(User.class);
        Order order = Order.asc("loginName");
        criteria.addOrder(order);

        List<User> result = criteria.list();

        HibernateUtil.commitTransaction();

        for (User u : result) {
            System.out.println(u);
        }
    }
    
    public static void restrictions() {
        Session session = HibernateUtil.beginTransaction();

        User user = new User();

        Example example = Example.create(user);
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.gt("id", 5L));

        List<User> result = criteria.list();

        HibernateUtil.commitTransaction();

        for (User u : result) {
            System.out.println(u);
        }
    }
    
    public static void creterions() {
        Session session = HibernateUtil.beginTransaction();

        Criterion c1 = Restrictions.isNotNull("emailAddress");
        Criterion c2 = Restrictions.gt("id", 2L);
        Criterion c3 = Restrictions.lt("id", 8L);
        
        User user = new User();
        user.setEmailAddress(".com");

        Example c4 = Example.create(user);
        c4.enableLike(MatchMode.END);
        c4.ignoreCase();
        
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(c1);
        criteria.add(c2);
        criteria.add(c3);
        criteria.add(c4);

        List<User> result = criteria.list();

        HibernateUtil.commitTransaction();

        for (User u : result) {
            System.out.println(u);
        }
    }
    
    public static List<User> findByCriterion(Criterion... criterions) {
        
        Session session = HibernateUtil.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        
        for(Criterion criterion : criterions) {
            criteria.add(criterion);
        }
        
        List<User> result = criteria.list();
        
        return result;
    }
}
