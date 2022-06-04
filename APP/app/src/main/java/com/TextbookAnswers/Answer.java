package com.textbookanswers;

public class Answer {

    public int id,questionId;
    public String question,answer;
    public int upvotes,downvotes;

    public Answer(String question, String answer, int upvotes, int downvotes) {
        this.question = question;
        this.answer = answer;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }
}
