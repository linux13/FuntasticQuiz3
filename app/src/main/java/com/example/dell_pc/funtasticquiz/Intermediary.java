package com.example.dell_pc.funtasticquiz;

public class Intermediary {
    QuizLibrary quizLibrary;
    QuizLibrary1 quizLibrary1;
    QuizLibrary2 quizLibrary2;
    Intermediary(){

    }
    public String getQuestion(int key,int index){
        String question="";
        if(key==1){
            quizLibrary=new QuizLibrary();
            question=quizLibrary.getQuestion(index);
        }
        else if(key==2){
            quizLibrary1=new QuizLibrary1();
            question=quizLibrary1.getQuestion(index);
        }
        else{
            quizLibrary2=new QuizLibrary2();
            question=quizLibrary2.getQuestion(index);
        }
        return question;
    }

    public String getOp1(int key,int index){
        String option="";
        if(key==1){
            quizLibrary=new QuizLibrary();
            option=quizLibrary.getChoice1(index);
        }
        else if(key==2){
            quizLibrary1=new QuizLibrary1();
            option=quizLibrary1.getChoice1(index);
        }
        else{
            quizLibrary2=new QuizLibrary2();
            option=quizLibrary2.getChoice1(index);
        }
        return  option;
    }

    public String getOp2(int key,int index){
        String option="";
        if(key==1){
            quizLibrary=new QuizLibrary();
            option=quizLibrary.getChoice2(index);
        }
        else if(key==2){
            quizLibrary1=new QuizLibrary1();
            option=quizLibrary1.getChoice2(index);
        }
        else{
            quizLibrary2=new QuizLibrary2();
            option=quizLibrary2.getChoice2(index);
        }
        return  option;
    }
    public String getOp3(int key,int index){
        String option="";
        if(key==1){
            quizLibrary=new QuizLibrary();
            option=quizLibrary.getChoice3(index);
        }
        else if(key==2){
            quizLibrary1=new QuizLibrary1();
            option=quizLibrary1.getChoice3(index);
        }
        else{
            quizLibrary2=new QuizLibrary2();
            option=quizLibrary2.getChoice3(index);
        }
        return  option;
    }
    public String getOp4(int key,int index){
        String option="";
        if(key==1){
            quizLibrary=new QuizLibrary();
            option=quizLibrary.getChoice4(index);
        }
        else if(key==2){
            quizLibrary1=new QuizLibrary1();
            option=quizLibrary1.getChoice4(index);
        }
        else{
            quizLibrary2=new QuizLibrary2();
            option=quizLibrary2.getChoice4(index);
        }
        return  option;
    }
    public long getIVS(int key,int index) {  //IVS=image,vidoe,sound
        long numb = 0;
        if (key == 1) {
            quizLibrary = new QuizLibrary();
            numb = quizLibrary.getIVS(index);
        } else if (key == 2) {
            quizLibrary1 = new QuizLibrary1();
            numb = quizLibrary1.getIVS(index);
        } else {
            quizLibrary2 = new QuizLibrary2();
            numb = quizLibrary2.getIVS(index);
        }

        return numb;
    }
    public String getCorrectAns(int key,int index){
        String crcAns="";
        if(key==1){
            quizLibrary=new QuizLibrary();
            crcAns=quizLibrary.getCorrectAnswer(index);
        }
        else if(key==2){
            quizLibrary1=new QuizLibrary1();
            crcAns=quizLibrary1.getCorrectAnswer(index);
        }
        else{
            quizLibrary2=new QuizLibrary2();
            crcAns=quizLibrary2.getCorrectAnswer(index);
        }
        return  crcAns;
    }

    public String getHint(int key,int index){
        String hint="";
        if(key==1){
            quizLibrary=new QuizLibrary();
            hint=quizLibrary.getHints(index);
        }
        else if(key==2){
            quizLibrary1=new QuizLibrary1();
            hint=quizLibrary1.getHints(index);
        }
        else{
            quizLibrary2=new QuizLibrary2();
            hint=quizLibrary2.getHints(index);
        }
        return hint;

    }
}
