/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model.genericdao.dao.factory;

import org.hibernate.dao.HibernateUtil;
import org.hibernate.model.genericdao.dao.AddressDao;
import org.hibernate.model.genericdao.dao.ClientDao;
import org.hibernate.model.genericdao.dao.ClientDetailDao;
import org.hibernate.model.genericdao.dao.SkillDao;
import org.hibernate.model.genericdao.model.Address;
import org.hibernate.model.genericdao.model.Client;
import org.hibernate.model.genericdao.model.ClientDetail;
import org.hibernate.model.genericdao.model.Skill;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
public class SkillManagerApp {
    public static void main(String args[]) {
        HibernateUtil.recreateDatabase();
        DaoFactory factory = DaoFactory.getFactory();
        factory.getClientDao().beginTransaction();
        
        ClientDao clientDao = factory.getClientDao();
        ClientDetailDao clientDetailDao = factory.getClientDetailDao();
        SkillDao skillDao = factory.getSkillDao();
        AddressDao addressDao = factory.getAddressDao();
        
        Client client = new Client();
        client.setUserName("me");
        client.setPassword("passw0rd");
        
        ClientDetail clientDetail = new ClientDetail();
        clientDetail.setEmailAddress("mail@scja.com");
        clientDetail.setFirstName("Cameron");
        clientDetail.setLastName("McKenzie");
        clientDetail.setClient(client);
               
        Address address = new Address();
        address.setAddressLine1("390 Queens Quay");
        address.setAddressLine2("apt 2301");
        address.setCity("Torronto");
        address.setCountry("Canada");
        address.setClient(client);
        
        client.getAddresses().add(address);
        
        Skill basting = new Skill();
        basting.setName("basting");
        Skill kicking = new Skill();
        kicking.setName("tire kicking");
        Skill polishing = new Skill();
        polishing.setName("shoe polishing");
        
        client.getSkills().add(basting);
        client.getSkills().add(kicking);
        client.getSkills().add(polishing);
        
        clientDao.save(client);
        clientDetailDao.save(clientDetail);
        addressDao.save(address);
        skillDao.save(basting);
        skillDao.save(kicking);
        skillDao.save(polishing);
        
        factory.getClientDao().commitTransaction();
    }
}
