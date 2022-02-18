package com.example.assignment2_version3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@RestController
public class QuestionController {

    Set<Question> question_set = new HashSet<>();
    ArrayList<String> mylist = new ArrayList<String>();
    Set<String> history = new HashSet<>();
    String response;
    int i;
    Integer GoldenID;
    Boolean flag =false;
    Boolean flag2 =false;
    String[] zeros = {"Not Questions yet"};




    @Autowired
    private HistoryRepository repository2;

    @Autowired
    private QuestionRepository repository;

    @Autowired
    private AnswerRepository repository3;






    @GetMapping("/listquestions")
    public Iterable<Question> getQuestions() {
        return repository.findAll();
    }


    @GetMapping("/historylist")
    public Iterable<History_Class> getHistory() {
        return repository2.findAll();
    }


    @GetMapping("/answerlist")
    public Iterable<Answer> getAnswer() {
        Answer an = new Answer();

        for (Question que : repository.findAll()) {
            System.out.println("Empika sto for answer re");

            if ((que.getQuestionID() == GoldenID)) {
                GoldenID = -1;
                an.setAnswer_s(que.getAnswer());
                repository3.save(an);

            }


        }
        return repository3.findAll();
    }





    @GetMapping("/answer")
    public String getAnswer2() {

        System.out.println("Empika sto answer re");

        for (Question que : repository.findAll()) {
            System.out.println("Empika sto for answer re");

            if ((que.getQuestionID() == GoldenID)) {
                GoldenID = -1;
              //  System.out.println("The Golden ID = "+GoldenID+" The Idquestion einai: "+que.getQuestionID());
               return "The answer is: " + que.getAnswer();


            }
        }
        return "llalalla";
    }




    @GetMapping("/addquestion")
    public RedirectView addQuestion(@RequestParam final String keywords_string, String answer) {
        Integer tempID;

        String[] temp_keywords = keywords_string.split(",");
        Question question = new Question();
        question.setAnswer(answer);
        question.setKeywords(temp_keywords);
        question.setKeywords_string(keywords_string);
        repository.save(question);
        tempID = question.getQuestionID();

        for (i = 0; i < temp_keywords.length; i++) {
            mylist.add(temp_keywords[i]);
        }


        question_set.add(new Question(tempID, temp_keywords, answer));



        return new RedirectView("http://localhost:8080/knowledge.html");

    }

    @GetMapping("/askquestion")
    public RedirectView addQuestion(@RequestParam final String response_string) {
        flag=true;



        repository3.deleteAll();
        response = response_string;
       question2array(response);
       history.add(response);
       History_Class h = new History_Class();
        h.setHistory_s(response_string);
        repository2.save(h);
       return new RedirectView("index2.html");
    }






    @GetMapping("/deletequestion")
    public RedirectView deleteQuestion(@RequestParam Integer questionID) {
        repository.deleteById(questionID);
        getQuestions();
        return new RedirectView("http://localhost:8080/knowledge.html");

    }

    private static String convertStringArrayToString(String[] strArr, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (String str : strArr)
            sb.append(str).append(delimiter);
        return sb.substring(0, sb.length() - 1);
    }

    public void question2array(String q) {
        int k = 0;
        String[] match = new String[20];
        String[] array = q.split(" ");

        for (int i = 0; i < array.length; i++) {

            if (mylist.contains(array[i])) {
                match[k] = array[i];
                k++;
            }
        }

        printanswer(match);


    }

    public void printanswer(String[] array) {

        i = 0;
        boolean flag = false;
        int  j = 0;

        array = Arrays.stream(array).distinct().toArray(String[]::new);

        int counter = 0;
        while (array[i] != null) {
            counter++;
            i++;
        }


        Question[] q = question_set.stream().toArray(Question[]::new);

        int a;
        int b;
        int k;
        int num =0;
        String [] final_keywords;



        for (a = 0; a < q.length; a++) {
            for (b = 0; b<q[a].getKeywords().length; b++){
                for (k=0;k< array.length;k++) {
                    if (q[a].getKeywords()[b].equals(array[k])) {
                        num++;
                    }

                }
            }


            if (q[a].getKeywords().length == num){
                System.out.println("Answer: " + q[a].getAnswer());

                flag = true;
                GoldenID=q[a].getQuestionID();

            }
            num= 0;

        }


    }


    public String repository_answer(int ID){

        for ( Question que :repository.findAll()){
            if (ID == que.getQuestionID())
                return que.getAnswer();
        }


        return null;
    }




}




