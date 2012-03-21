/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.Session;
import org.hibernate.dao.HibernateUtil;

/**
 * Przykład dwóch klas i jednej tabeli
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@Entity
public class Thing {
    
    private long id;
    private String name;
    
    private ThingDetail thingDetail;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Embedded
    public ThingDetail getThingDetail() {
        return thingDetail;
    }

    public void setThingDetail(ThingDetail thingDetail) {
        this.thingDetail = thingDetail;
    }
    
    public static void main(String args []) {
        HibernateUtil.recreateDatabase();
        
        ThingDetail thingDetail = new ThingDetail();
        thingDetail.setAlias("Joey Shabiddo");
        thingDetail.setCount(10);
        
        Thing thing = new Thing();
        thing.setName("Homer");
        thing.setThingDetail(thingDetail);
        
        Session session = HibernateUtil.beginTransaction();
        session.save(thing);
        
        HibernateUtil.commitTransaction();
    }
}
