/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model.genericdao.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.model.genericdao.model.Client;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
public interface ClientDao extends GenericDAO<Client, Long> {
    public List<Client> findAllVerified();
}
