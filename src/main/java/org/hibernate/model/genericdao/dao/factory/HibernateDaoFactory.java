/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model.genericdao.dao.factory;

import org.hibernate.model.genericdao.dao.AddressDao;
import org.hibernate.model.genericdao.dao.ClientDao;
import org.hibernate.model.genericdao.dao.ClientDetailDao;
import org.hibernate.model.genericdao.dao.SkillDao;
import org.hibernate.model.genericdao.dao.hibernate.HibernateAddressDao;
import org.hibernate.model.genericdao.dao.hibernate.HibernateClientDao;
import org.hibernate.model.genericdao.dao.hibernate.HibernateClientDetailDao;
import org.hibernate.model.genericdao.dao.hibernate.HibernateSkillDao;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
public class HibernateDaoFactory extends DaoFactory {

    @Override
    public AddressDao getAddressDao() {
        return new HibernateAddressDao();
    }

    @Override
    public ClientDao getClientDao() {
        return new HibernateClientDao();
    }

    @Override
    public ClientDetailDao getClientDetailDao() {
        return new HibernateClientDetailDao();
    }

    @Override
    public SkillDao getSkillDao() {
        return new HibernateSkillDao();
    }
}
