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
        ApiRequest request = new ApiRequest("/Question/"+chapterId);
        try {
            request.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<Exercise> b= new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(request.response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                b.add(new Exercise(chapterId,json.getInt("questionNumber")){
                    {
                        id = json.getInt("id");
                        question = json.getString("text");
                    }
                });
                //update recycler with questoin number
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return b;
    }

    public static ArrayList<Answer> getAnswers(int exerciseId){
        ApiRequest request = new ApiRequest("/Answer/"+exerciseId);
        try {
            request.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<Answer> b= new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(request.response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                b.add(new Answer(json.getString("question"),json.getString("text"),json.getInt("upvotes"),json.getInt("downvotes")));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return b;
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
        ApiRequest request = new ApiRequest("/Question/"+chapterId);
        try {
            request.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            JSONArray jsonArray = new JSONArray(request.response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                if(json.getInt("questionNumber") == exerciseNumber)
                    return json.getInt("id");

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static int getChapterId(int bookId,int chapterNumber){
        ApiRequest request = new ApiRequest("/Chapter/"+bookId);
        try {
            request.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            JSONArray jsonArray = new JSONArray(request.response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                if(json.getInt("chapterNumber") == chapterNumber)
                    return json.getInt("id");

            }

        } catch (JSONException e) {
            e.printStackTrace();
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
