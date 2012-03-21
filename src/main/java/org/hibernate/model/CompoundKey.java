/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * Klucze złożone
 * Klasy typu CompoundKey muszą implementować interfejs Serializable
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@Embeddable
public class CompoundKey implements Serializable{
    
    private Long userId;
    private Long bankId;

    public CompoundKey() {
    }

    public Long getBankId() {
        return bankId;
    }

    public CompoundKey(Long userId, Long bankId) {
        this.userId = userId;
        this.bankId = bankId;
    }
    

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object key) {
        boolean result = true;
        
        if (!(key instanceof CompoundKey)) {
            return false;
        }
        
        Long otherUserId = ((CompoundKey)key).getUserId();
        Long otherBankId = ((CompoundKey)key).getBankId();
        
        if (bankId == null || otherBankId == null) {
            return false;
        } else {
            result = bankId.equals(otherBankId);
            if (!result) {
                return false;
            }
        }
        
        if(userId == null || otherUserId == null) {
            return false;
        } else {
            result = userId.equals(otherUserId);
        }
        
        return result;
    }

    @Override
    public int hashCode() {
        int code = 0;
        
        if (userId != null) {
            code += userId;
        }
        
        if(bankId != null) {
            code += bankId;
        }
        
        return code;
    }
           
    @Override
    public String toString() {
        return "CompoundKey{" + "userId=" + userId + ", bankId=" + bankId + '}';
    }
    
    public static void main(String args[]) {
        CompoundKey k1 = new CompoundKey(2l, 1l);
        CompoundKey k2 = new CompoundKey(2l, 2l);
        
        System.out.println(k1.equals(k2));
    }
}
