package com.textbookanswers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Repository {

    //testes
    public static ArrayList<Book> books = new ArrayList<>();
    public static ArrayList<Chapter> chapters = new ArrayList<>();
    public static ArrayList<Exercise> exercises = new ArrayList<>();
    public static ArrayList<Answer> answers = new ArrayList<>();

    public static void init(){
        books.add(new Book("Livro 1"){
            {
                author = "joao";
                id = 0;
            }
        });


        for (int i = 0; i < 10; i++) {
            chapters.add(new Chapter(i,0,i,"capitlo "+i));
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                final int a = i;
                final int b = j;
                exercises.add(new Exercise(i,j){
                    {
                        id = 10*a +b;
                        question = "pergunta id="+id;
                    }
                });
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {

                    try{
                    answers.add(new Answer(100*i+10*j+k,10*i+j,"resposta "+(100*i+10*j+k),12,34));
                    }
                    catch (Exception e){
                        int a = 32;
                    }
                }
            }
        }
    }

    public static ArrayList<Chapter> getChapters(int bookId){
        ApiRequest request = new ApiRequest("/Chapter/"+Integer.toString(bookId));
        try {
            request.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<Chapter> b= new ArrayList<>();

        try {
            JSONArray bookJson = new JSONArray(request.response);
            for (int i = 0; i < bookJson.length(); i++) {
                JSONObject json = bookJson.getJSONObject(i);
                b.add(new Chapter(json.getInt("id"),bookId,json.getInt("chapterNumber"),json.getString("name")));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return b;
    }

    public static ArrayList<Exercise> getExercises(int chapterId){
        //return todos os exercicio do capitulo com id chapterId

        ArrayList<Exercise> c = new ArrayList<>();
        for (Exercise exercise : exercises) {
            if(exercise.chapterId == chapterId)
                c.add(exercise);
        }

        return c;
    }

    public static ArrayList<Answer> getAnswers(int exerciseId){
        //retorna todas as repostas do exercicio com id exerciseId

        ArrayList<Answer> a = new ArrayList<>();
        for(Answer answer : answers){
            if(answer.questionId == exerciseId)
                a.add(answer);
        }

        return a;
    }

    public static ArrayList<Book> searchBooks(String term){

        ApiRequest request = new ApiRequest("/Book");
        try {
            request.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<Book> b= new ArrayList<>();

        try {
            JSONArray bookJson = new JSONArray(request.response);
            for (int i = 0; i < bookJson.length(); i++) {
                JSONObject bookObject = bookJson.getJSONObject(i);
                if(bookObject.getString("title").toLowerCase().contains(term.toLowerCase()))
                    b.add(new Book(bookObject.getInt("id"),bookObject.getString("title"),bookObject.getString("author")));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return b;
    }

    public static ArrayList<Book> getMostPopularBooks(){

        return searchBooks("");
    }


    public static int getExerciseId(int chapterId,int exerciseNumber){
        //retorna o id do exercicio exerciseNumber do capitlo com id = chapterId

        for(Exercise exercise : exercises){
            if(exercise.chapterId == chapterId && exercise.number == exerciseNumber)
                return exercise.id;
        }

        return -1;
    }

    public static int getChapterId(int bookId,int chapterNumber){
        for(Chapter chapter : chapters){
            if(chapter.bookId == bookId && chapter.number == chapterNumber)
                return chapter.id;
        }

        return -1;
    }

    public static Exercise getExercise(int exerciseId){
        for(Exercise exercise : exercises){
            if(exercise.id == exerciseId)
                return exercise;
        }

        return null;
    }

    public static Answer getAnswer(int exerciseId,int number){
        return new Answer("","",0,0);
    }

    public static void upvote(Answer answer){

    }

    public static void downvote(Answer answer){

    }



    public static boolean validateUser(User user){
        return true;
    }

    public static boolean registerUser(User user){
        return true;
    }

    public static boolean addAnswer(String answer,String exerciseId){
        return true;
    }

}
