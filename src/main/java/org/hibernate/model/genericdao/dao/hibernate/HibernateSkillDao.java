/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model.genericdao.dao.hibernate;

import org.hibernate.model.genericdao.dao.SkillDao;
import org.hibernate.model.genericdao.model.Skill;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
public class HibernateSkillDao extends HibernateDao<Skill, Long> implements SkillDao {
    
    public HibernateSkillDao() {
        super(Skill.class);
    }
}
