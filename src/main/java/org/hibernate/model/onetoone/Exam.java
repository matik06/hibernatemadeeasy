/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model.onetoone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.dao.HibernateUtil;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@Entity
@Table(name="exam", schema="examscam")
public class Exam {
    
    private int id;
    private String shortName;
    private ExamDetail detail;

    /**
     * CascadeType.PERSIST - dla metody session.save zapisane zostaną wszystkie referencje do innych obiektów
     * CascadeType.REMOVE - jeśli użyjemy session.remove to usunięte zostanie usunięty zostanie z bazy wpis dotyczący referencji do innego obiektu
     * CascadeType.REFRESH - zepewnia, że obiekt zostanie również odświeżony wraz z głównym obiektem
     * CascadeType.MERGE - session.merge(...)
     * CascadeType.ALL = ={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.MERGE
     */
    
    /**
     * FetchType.LAZY - dane wczytywane są z bazy dopiero podczas pierwszego ich użycia
     * FetchType.EAGER - dane wczytywane są zawsze przy wczytaniu głównego obiektu
     */
    
    //hibernate doda foregin key detail_id do tabeli exam_detail
    @OneToOne(cascade={CascadeType.ALL}, fetch= FetchType.EAGER)
    @JoinColumn(name="detaill_id")  //określa nazwę klucza obcego w tabeli exam
    public ExamDetail getDetail() {
        return detail;
    }

    public void setDetail(ExamDetail detail) {
        this.detail = detail;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "Exam{" + "id=" + id + ", shortName=" + shortName + ", detail=" + detail + '}';
    }
    
    public static void main(String args[]) {
        HibernateUtil.recreateDatabase();
        
        Exam exam = new Exam();
        exam.setShortName("SCJA");
        
        ExamDetail examDetail = new ExamDetail();
        examDetail.setFullName("Sun certified Java Associate");
        examDetail.setPassingPercentages(62);
        examDetail.setNumberOfQuestions(55);
        exam.setDetail(examDetail);
        
        Session session = HibernateUtil.beginTransaction();
        session.save(exam);
        HibernateUtil.commitTransaction();
    }
}
