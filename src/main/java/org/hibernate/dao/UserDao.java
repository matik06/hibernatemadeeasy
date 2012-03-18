/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.dao;

import java.util.List;
import org.hibernate.model.User;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
public interface UserDao {
    
    User create(User user);
    boolean update(User user);
    boolean delete(User user);
    User findByPrimaryKey(Long primaryKey);
    List<User> findByExample(User user, boolean fuzzy);
    List<User> findAll();
}
