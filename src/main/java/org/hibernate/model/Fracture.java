/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import org.hibernate.Session;
import org.hibernate.dao.HibernateUtil;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@Entity
@IdClass(org.hibernate.model.CompoundKey.class)
public class Fracture {
    Long bankId;
    Long userId;
    String bone;

    @Id
    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    @Id
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBone() {
        return bone;
    }

    public void setBone(String bone) {
        this.bone = bone;
    }
    
    public static void main(String args[]) {
        Fracture bone = new Fracture();
        bone.setBone("arm");
        bone.setBankId(99L);
        bone.setUserId(88L);

        HibernateUtil.recreateDatabase();

        Session session = HibernateUtil.beginTransaction();
        session.save(bone);
        HibernateUtil.commitTransaction();
    }
}
