/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model.genericdao.dao.hibernate;

import java.util.List;
import org.hibernate.model.genericdao.dao.ClientDao;
import org.hibernate.model.genericdao.model.Client;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
public class HibernateClientDao extends HibernateDao<Client, Long> implements ClientDao{

    public HibernateClientDao() {
        super(Client.class);
    }

    public List<Client> findAllVerified() {
        Client client = new Client();
        client.setVerified(true);
        return findByExample(client);
    }
    
    
}
