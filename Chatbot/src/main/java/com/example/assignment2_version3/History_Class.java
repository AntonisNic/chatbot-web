package com.example.assignment2_version3;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class History_Class {



        @Id
        @GeneratedValue
        private Integer h_ID;

    public Integer getH_ID() {
        return h_ID;
    }

    public void setH_ID(Integer h_ID) {
        this.h_ID = h_ID;
    }

    public String getHistory_s() {
        return history_s;
    }

    public void setHistory_s(String history_s) {
        this.history_s = history_s;
    }

    private String history_s;






}
