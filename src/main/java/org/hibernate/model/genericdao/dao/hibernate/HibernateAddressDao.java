/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model.genericdao.dao.hibernate;

import org.hibernate.model.genericdao.dao.AddressDao;
import org.hibernate.model.genericdao.model.Address;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
public class HibernateAddressDao extends HibernateDao<Address, Long> implements AddressDao {

    public HibernateAddressDao() {
        super(Address.class);
    }
    
}
