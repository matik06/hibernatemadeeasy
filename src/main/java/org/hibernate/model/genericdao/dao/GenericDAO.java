/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model.genericdao.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
public interface GenericDAO<T, ID extends Serializable> {
    
    T findByPrimaryKey(ID id);
    List<T> findAll(int stardIndex, int fetchSize);
    List<T> findByExample(T exampleInstance, String[] excludeProperty);
    
    T save(T entity);
    void delete(T entity);
    
    void beginTransaction();
    void commitTransaction();
}
