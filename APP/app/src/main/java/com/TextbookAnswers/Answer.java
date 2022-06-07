package com.textbookanswers;

public class Answer {

    public int id,questionId;

    //numero da resposta de acordo com quantas o exercicio tem
    public int number;
    public String question,answer;
    public int upvotes,downvotes;

    public Answer(int id,int questionId,String answer, int upvotes, int downvotes) {
        this.id = id;
        this.questionId = questionId;
        this.question = Repository.getExercise(questionId).question;
        this.answer = answer;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

    public Answer(String question, String answer, int upvotes, int downvotes) {
        this.question = question;
        this.answer = answer;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }
}
