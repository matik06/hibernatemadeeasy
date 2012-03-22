/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model.inheritance;

import javax.persistence.Entity;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@Entity
public class Child extends Parent {
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
