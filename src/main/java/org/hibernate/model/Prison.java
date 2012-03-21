/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import org.hibernate.Session;
import org.hibernate.dao.HibernateUtil;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@Entity
public class Prison {

    private String city;
    private CompoundKey id;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @EmbeddedId
    public CompoundKey getId() {
        return id;
    }

    public void setId(CompoundKey id) {
        this.id = id;
    }
    public static void main(String args[]) {
        Prison jail = new Prison();
        jail.setCity("Milhaven");
        Long wayne = 99L;
        Long mario = 88L;
        CompoundKey key = new CompoundKey(wayne, mario);
        jail.setId(key);

        HibernateUtil.recreateDatabase();

        Session session = HibernateUtil.beginTransaction();
        session.save(jail);
        HibernateUtil.commitTransaction();
    }
}
