package com.example.assignment2_version3;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Question {


@Id
@GeneratedValue
private Integer questionID;


    private String[] keywords = new String[10];



    private String keywords_string;
    private String answer;



    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getKeywords_string() {
        return keywords_string;
    }

    public void setKeywords_string(String keywords_string) {
        this.keywords_string = keywords_string;
    }


    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public Question(Integer questionID, String[] keywords, String answer) {
        this.questionID = questionID;
        this.keywords = keywords;
        this.answer = answer;
    }

    public Question() {
    }
}
