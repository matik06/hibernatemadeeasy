/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.dao;

import org.hibernate.Session;


/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
public abstract class ExamScamDao {
    
    protected Session getSession() {
        return HibernateUtil.getSession();
    }
    
    protected void save(Object pojo) {
        Session session = this.getSession();
        session.save(pojo);
    }
    
    protected void delete(Object pojo) {
        Session session = this.getSession();
        session.delete(pojo);
    }
    
    protected Object findByPrimaryKey(Class clazz, Long primaryKey) {
        
        Session session = this.getSession();
        Object pojo = session.get(clazz, primaryKey);
        
        return pojo;
    }
}
