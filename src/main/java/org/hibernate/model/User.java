/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@Entity
@Table(name="User", schema="examscam")
@NamedQuery(name="findById", query="from User where id = :id")
public class User {
    
    private Long id;
    private String password;
    private String loginName;
    private String encryptedPassword;
    
    private String emailAddress;
    private Boolean verified;
    private Date lastAccessTime;
    private Calendar registrationDate;


    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="login_name")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Transient
    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    @Temporal(TemporalType.DATE)
    public Calendar getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Calendar registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Basic
    public Boolean isVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", password=" + password + ", loginName=" + loginName + ", emailAddress=" + emailAddress + '}';
    }
}
