package com.textbookanswers;

import java.util.ArrayList;

public class Repository {

    public static ArrayList<Exercise> getExercises(int chapterId){

        ArrayList<Exercise> exercises = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            exercises.add(new Exercise(0,i,"Resposta"+i));
        }

        return exercises;
    }

    public static ArrayList<Chapter> getChapters(int bookId){

        ArrayList<Chapter> exercises = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            exercises.add(new Chapter(bookId,i,"nome"+i));
        }

        return exercises;
    }

    public static ArrayList<Book> searchBooks(String term){

        ArrayList<Book> exercises = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            exercises.add(new Book(term+i));
        }

        return exercises;
    }
}
