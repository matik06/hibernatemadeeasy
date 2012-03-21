/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.dao;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.model.User;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
public class HibernateUserDao extends ExamScamDao implements UserDao {

    public User create(User user) {
        
        if (user.getId() != null && user.getId() != 0) {
            user = null;
        } else {
            user.setLastAccessTime(new Date());
            user.setRegistrationDate(new GregorianCalendar());
            user.setVerified(false);
            super.save(user);
        }
        
        return user;
    }

    public boolean delete(User user) {
        boolean successFlag = true;
        
        try {
            user.setPassword("");
            super.delete(user);
        } catch (Throwable th) {
            successFlag = false;
        }
        
        return successFlag;
    }

    public List<User> findAll() {
        String query = "from User";
        Query queryResult = this.getSession().createQuery(query);
        List<User> allUser = queryResult.list();
        
        return allUser;
    }

    public List<User> findByExample(User user, boolean fuzzy) {
        Session session = this.getSession();
        
        Criteria criteria = session.createCriteria(User.class);
        Example example = Example.create(user);
        
        if (fuzzy) {
            example.enableLike(MatchMode.ANYWHERE);
            example.ignoreCase();
            example.excludeZeroes();
        }
        
        criteria.add(example);
        List<User> users = criteria.list();
        
        return users;
    }

    public User findByPrimaryKey(Long primaryKey) {
        User user = (User)super.findByPrimaryKey(User.class, primaryKey);
        return user;
    }

    public boolean update(User user) {
        boolean successFlag = true;
        
        try {
            if (user.getId() == null || user.getId() == 0) {
                successFlag = false;
            } else {
                super.save(user);
            }
        } catch (Throwable th) {
            successFlag = false;
        }
        
        return successFlag;
    }
    
}
