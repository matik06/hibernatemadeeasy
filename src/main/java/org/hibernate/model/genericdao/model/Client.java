/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model.genericdao.model;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@Entity
@Table(name="client", schema="examscam")
public class Client implements Serializable{
    
    private List<Address> addresses = new Vector<Address>();
    private List<Skill> skills = new Vector<Skill>();
    private ClientDetail clientDetail;
    private Long id;
    private String userName;
    private String password;
    private Boolean verified;
    
    @ManyToMany(cascade= CascadeType.PERSIST)
    @JoinTable(name="client_skill",
            joinColumns={@JoinColumn(name="client_id")},
            inverseJoinColumns={@JoinColumn(name="skill_id")})
    public List<Skill> getSkills() {
        return skills;
    }

    @OneToMany(mappedBy="client",
            fetch= FetchType.EAGER,
            cascade= {CascadeType.ALL},
            orphanRemoval=true)
    public List<Address> getAddresses() {
        return addresses;
    }
    
    public void addAddress(Address address) {
        address.setClient(this);
        addresses.add(address);
    }
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "detail_id")
    public ClientDetail getClientDetail() {
        return clientDetail;
    }

    public void setClientDetail(ClientDetail clientDetail) {
        this.clientDetail = clientDetail;
    }
    
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

   

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
    
    
}
