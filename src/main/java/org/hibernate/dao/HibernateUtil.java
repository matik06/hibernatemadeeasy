package org.hibernate.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.model.inheritance.Ancestor;
import org.hibernate.model.inheritance.Child;
import org.hibernate.model.FooBar;
import org.hibernate.model.Fracture;
import org.hibernate.model.Interest;
import org.hibernate.model.inheritance.Parent;
import org.hibernate.model.Prison;
import org.hibernate.model.Snafu;
import org.hibernate.model.Thing;
import org.hibernate.model.User;
import org.hibernate.model.genericdao.model.Address;
import org.hibernate.model.genericdao.model.Client;
import org.hibernate.model.genericdao.model.ClientDetail;
import org.hibernate.model.genericdao.model.Skill;
import org.hibernate.model.inheritance.manytomany.LeftManyStudent;
import org.hibernate.model.inheritance.manytomany.RightManyCourse;
import org.hibernate.model.onetomany.Player;
import org.hibernate.model.onetomany.Team;
import org.hibernate.model.onetoone.Exam;
import org.hibernate.model.onetoone.ExamDetail;
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
        configuration.addAnnotatedClass(Snafu.class);
        configuration.addAnnotatedClass(FooBar.class);
        configuration.addAnnotatedClass(Thing.class);
        configuration.addAnnotatedClass(Interest.class);
        configuration.addAnnotatedClass(Fracture.class);
        configuration.addAnnotatedClass(Prison.class);
        configuration.addAnnotatedClass(Ancestor.class);
        configuration.addAnnotatedClass(Parent.class);
        configuration.addAnnotatedClass(Child.class);
        configuration.addAnnotatedClass(Exam.class);
        configuration.addAnnotatedClass(ExamDetail.class);
        configuration.addAnnotatedClass(Team.class);
        configuration.addAnnotatedClass(Player.class);
        configuration.addAnnotatedClass(LeftManyStudent.class);
        configuration.addAnnotatedClass(RightManyCourse.class);
        
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Client.class);
        configuration.addAnnotatedClass(ClientDetail.class);
        configuration.addAnnotatedClass(Skill.class);
        
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
