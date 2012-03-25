/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model.genericdao.dao.hibernate;

import org.hibernate.model.genericdao.dao.ClientDetailDao;
import org.hibernate.model.genericdao.model.ClientDetail;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
public class HibernateClientDetailDao extends HibernateDao<ClientDetail, Long> implements ClientDetailDao {

    public HibernateClientDetailDao() {
        super(ClientDetail.class);
    }
    
}
