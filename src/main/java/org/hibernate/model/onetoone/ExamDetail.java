/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate.model.onetoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@Entity
@Table(name="exam_detail", schema="examscam")
public class ExamDetail {
    
    private int id;
    private String fullName;
    private int numberOfQuestions;
    private int passingPercentages;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Id
    @GeneratedValue
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getPassingPercentages() {
        return passingPercentages;
    }

    public void setPassingPercentages(int passingPercentages) {
        this.passingPercentages = passingPercentages;
    }

    @Override
    public String toString() {
        return "ExamDetail{" + "id=" + id + ", fullName=" + fullName + ", numberOfQuestions=" + numberOfQuestions + ", passingPercentages=" + passingPercentages + '}';
    }

    
    private Exam exam;

    //jeśli chcemy mieć relację jeden do jednego w obu kierunkach to dodajemy referencję do głównego obiektu
    //wraz z adnotacją OneToOne z atrybutem mappedBy
    @OneToOne(mappedBy="detail")
    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
