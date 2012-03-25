/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model.genericdao.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.dao.HibernateUtil;
import org.hibernate.model.genericdao.dao.GenericDAO;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
public abstract class HibernateDao<T, ID extends Serializable> implements GenericDAO<T, ID> {
    
    private Class<T> persistentClass;
    
    public HibernateDao(Class c) {
        this.persistentClass = c;
    }
    
    @Override
    public T findByPrimaryKey(ID id) {
        return (T) HibernateUtil.getSession().load(persistentClass, id);
    }

    @Override
    public T save(T entity) {
        HibernateUtil.getSession().save(entity);
        return entity;
    }
    
    @Override
    public void delete(T entity) {
        HibernateUtil.getSession().delete(entity);
    }
    
    public void beginTransaction() {
        HibernateUtil.beginTransaction();
    }

    public void commitTransaction() {
        HibernateUtil.commitTransaction();
    }

    public List<T> findAll(int stardIndex, int fetchSize) {
        Criteria crit = HibernateUtil.getSession().createCriteria(persistentClass);
        
        crit.setFirstResult(stardIndex);
        crit.setFetchSize(fetchSize);
        
        return crit.list();
    }

    public List<T> findByExample(T exampleInstance, String... excludeProperty) {
        
        Criteria crit = HibernateUtil.getSession().createCriteria(persistentClass);        
        Example example = Example.create(exampleInstance);
        
        if (excludeProperty != null && excludeProperty instanceof String[]) {
            for (int i = 0; i < excludeProperty.length; i++) {
                example.excludeProperty(excludeProperty[i]);
            }
        }
        
        crit.add(example);
        return crit.list();
    }

    
    
    
}
