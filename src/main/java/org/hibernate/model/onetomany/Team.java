/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model.onetomany;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.Session;
import org.hibernate.dao.HibernateUtil;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@Entity
public class Team {
    
    private long id;
    private String name;
    private List<Player> players;

    public Team() {
        players = new ArrayList();
    }
    
    

    @OneToMany(mappedBy="team", //nazwa zmiennej zawierającą klasę Team
            targetEntity=Player.class,  //typ obiektów przechowywanych w kolekcji (chyba nie jest konieczne!)
            fetch= FetchType.LAZY,      //sposób wczytywania obiektów, jeśli odwołamy się do obiektu poza transakcją to dostaniemy błąd
            cascade= CascadeType.ALL)   //informacje jak mają zachować się obiektyz listy przy operacjach z session
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        
        for (Player player : players) {
            player.setTeam(this);
        }
    }
    
    public void addPlayer(Player player) {
        player.setTeam(this);
        this.players.add(player);
    }
    
    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    public static void main(String args[]) {
        HibernateUtil.recreateDatabase();
        
        Session session = HibernateUtil.beginTransaction();
        
//        Team team = new Team();
//        Player p1 = new Player();
//        Player p2 = new Player();
//        
//        session.save(team);
//        session.save(p1);
//        session.save(p2);
//        
//        team.setName("Pickering Atoms");
//        p1.setNickName("Lefty");
//        p1.setTeam(team);
//        p2.setNickName("Blinky");
//        p2.setTeam(team);
        
        Team team = new Team();
        Player p1 = new Player();
        Player p2 = new Player();
        
        p1.setNickName("Lefty");        
        p2.setNickName("Blinky");
        
        team.setName("Pickering Atoms");
        team.addPlayer(p1);
        team.addPlayer(p2);

        session.save(team);

        HibernateUtil.commitTransaction();
    }
}
