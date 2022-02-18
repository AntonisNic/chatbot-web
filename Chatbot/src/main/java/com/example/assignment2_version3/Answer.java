package com.example.assignment2_version3;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Answer {


    public Integer getA_ID() {
        return a_ID;
    }

    public void setA_ID(Integer a_ID) {
        this.a_ID = a_ID;
    }

    @Id
    @GeneratedValue
    private Integer a_ID;


    private String answer_s;



    public String getAnswer_s() {
        return answer_s;
    }

    public void setAnswer_s(String answer_s) {
        this.answer_s = answer_s;
    }


}
