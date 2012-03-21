/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.dao;

import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.model.User;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
public class AddUsers {
    
    public static void main(String args[]) {
        
        int keepAdding = 1;
        
        while(keepAdding == 1) {
            Scanner keyboard = new Scanner(System.in);
            User user = new User();
            System.out.println("login name: ");
            user.setLoginName(keyboard.next());
            System.out.println("email address: ");
            user.setEmailAddress(keyboard.next());
            System.out.println("password");
            user.setPassword(keyboard.next());

            try {
                HibernateUtil.beginTransaction();
                UserDao userDao = new HibernateUserDao();
                userDao.create(user);
                HibernateUtil.commitTransaction();
                System.out.println("user successfully added");
            } catch (HibernateException e) {
                e.printStackTrace();
                System.out.println("database inser failed");
                System.out.println(e.getClass() + e.getMessage());
            }

            System.out.println("continue adding?");
            keepAdding = keyboard.nextInt();
        }
    }
}
