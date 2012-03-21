/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model;

import javax.persistence.Embeddable;

/**
 * Przykład dwóch klas i jednej tabeli
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@Embeddable
public class ThingDetail {
    
    private String alias;
    private int count;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ThingDetail{" + "Alias=" + alias + ", count=" + count + '}';
    }
}
