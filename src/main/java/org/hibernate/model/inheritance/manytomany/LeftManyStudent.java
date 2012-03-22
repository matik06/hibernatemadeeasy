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
import org.hibernate.Session;
import org.hibernate.dao.HibernateUtil;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@Entity
public class LeftManyStudent {
    
    int id;
    String studentName;
    List<RightManyCourse> courses = new Vector();

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(name="join_table", 
            joinColumns={@JoinColumn(name="lmstudent_id")},
            inverseJoinColumns={@JoinColumn(name="rmcourse_id")})
    public List<RightManyCourse> getCourses() {
        return courses;
    }

    public void setCourses(List<RightManyCourse> courses) {
        this.courses = courses;
    }        

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public static void main(String args[]) {
        HibernateUtil.recreateDatabase();
        
        LeftManyStudent student1 = new LeftManyStudent();
        student1.setStudentName("Jim Jump");
        LeftManyStudent student2 = new LeftManyStudent();
        student2.setStudentName("Julie Camp");
        LeftManyStudent student3 = new LeftManyStudent();
        student3.setStudentName("Cam Johnson");
        LeftManyStudent student4 = new LeftManyStudent();
        student4.setStudentName("Marcus McKenzie");
        
        RightManyCourse java = new RightManyCourse();
        java.setCourseCode("java-101");
        RightManyCourse cpp = new RightManyCourse();
        cpp.setCourseCode("c++ 101");
        RightManyCourse math = new RightManyCourse();
        math.setCourseCode("math - 101");
        
//        java.getStudents().add(student1);
//        java.getStudents().add(student2);
//        java.getStudents().add(student3);
//        cpp.getStudents().add(student2);
//        cpp.getStudents().add(student3);
//        math.getStudents().add(student4);
        
        student1.getCourses().add(java);
        student2.getCourses().add(java);
        student3.getCourses().add(java);
        student2.getCourses().add(cpp);
        student3.getCourses().add(cpp);
        student4.getCourses().add(math);
        
        Session session = HibernateUtil.beginTransaction();
        
        session.save(student1);
        session.save(student2);
        session.save(student3);
        session.save(student4);
//        
//        session.save(java);
//        session.save(cpp);
//        session.save(math);
        
        HibernateUtil.commitTransaction();
    }
}
