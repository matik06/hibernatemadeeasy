/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model.inheritance.manytomany;

import java.util.List;
import java.util.Vector;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@Entity
public class RightManyCourse {
    
    long id;
    String courseCode;
    List<LeftManyStudent> students = new Vector();

    
    @ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(name="join_table", 
            joinColumns={@JoinColumn(name="rmcourse_id")},
            inverseJoinColumns={@JoinColumn(name="lmstudent_id")})
    public List<LeftManyStudent> getStudents() {
        return students;
    }

    public void setStudents(List<LeftManyStudent> students) {
        this.students = students;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
