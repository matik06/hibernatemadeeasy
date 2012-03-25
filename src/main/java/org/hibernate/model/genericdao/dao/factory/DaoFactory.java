/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model.genericdao.dao.factory;

import org.hibernate.model.genericdao.dao.AddressDao;
import org.hibernate.model.genericdao.dao.ClientDao;
import org.hibernate.model.genericdao.dao.ClientDetailDao;
import org.hibernate.model.genericdao.dao.SkillDao;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
public abstract class DaoFactory {
    
    public static final Class FACTORY_CLASS = HibernateDaoFactory.class;
    //public static final Class FACTORY_CLASS = JDBCFactory.class;
    
    public static DaoFactory getFactory() {
        try {
            return (DaoFactory)FACTORY_CLASS.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't create Factory");
        }
    }   
    
    public abstract ClientDao getClientDao();
    public abstract ClientDetailDao getClientDetailDao();
    public abstract SkillDao getSkillDao();
    public abstract AddressDao getAddressDao();
}
