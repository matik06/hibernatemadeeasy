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
public class Parent extends Ancestor{
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
