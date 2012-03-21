/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * InheritanceType.TABLE_PER_CLASS - dla każdej klasy zostanie utworzona nowa tabela z wszystkimi polami jakie posiada i jakie odziedziczył
 * InheritanceType.JOINED - utworzą się 3 tabele ale każda będzie posiadała id + tylko swoje pola
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@Entity
@Inheritance(strategy= InheritanceType.JOINED)
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
}
