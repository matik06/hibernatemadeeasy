/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model.inheritance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import org.hibernate.Session;
import org.hibernate.dao.HibernateUtil;

/**
 * InheritanceType.TABLE_PER_CLASS - dla każdej klasy zostanie utworzona nowa tabela z wszystkimi polami jakie posiada i jakie odziedziczył
 * InheritanceType.JOINED - utworzą się 3 tabele ale każda będzie posiadała id + tylko swoje pola
 * InheritanceType.SINGLE_TABLE - (najczęściej używane) wszystkie zmienne z trzech klas będą przechowywane w jednej tabeli (dodana zostanie specjalna kolumna DTYPE - przechowująca informacje o typie klasy powiązanej z danym rekordem)
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Ancestor {
    private Long id;
    private String nationality;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public static void main(String args []) {
        HibernateUtil.recreateDatabase();
        
        Ancestor a = new Ancestor();
        a.setNationality("Korean");
        
        Parent p = new Parent();
        p.setNationality("Jewish");
        p.setLastName("Steinberg");
        
        Child c = new Child();
        c.setNationality("Irish");
        c.setLastName("McKenzie");
        c.setFirstName("Cameron");
        
        Session session = HibernateUtil.beginTransaction();
        session.save(a);
        session.save(p);
        session.save(c);
        HibernateUtil.commitTransaction();
    }
}
