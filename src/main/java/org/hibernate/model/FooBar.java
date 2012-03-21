/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.dao.HibernateUtil;

/**
 * Przykład jednej klasy reprezentującej dwie tabele
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@Entity
@Table(name="bar")
@SecondaryTable(name="foo")
public class FooBar {
    
    int id;
    String fooName;
    String barCode;
    
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    @Column(table="foo")
    public String getFooName() {
        return fooName;
    }

    //no need for mapping-goes to default bar table
    public void setFooName(String fooName) {
        this.fooName = fooName;
    }

    @Override
    public String toString() {
        return "FooBar{" + "id=" + id + ", fooName=" + fooName + ", barCode=" + barCode + '}';
    }
    
    public static void main(String args[]) {
        HibernateUtil.recreateDatabase();
        
        FooBar fb = new FooBar();
        fb.setBarCode("90210");
        fb.setFooName("ManChu");
        
        Session session = HibernateUtil.beginTransaction();
        session.save(fb);
        HibernateUtil.commitTransaction();
    }
}
