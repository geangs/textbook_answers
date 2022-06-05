package com.textbookanswers;

import java.util.ArrayList;

public class Repository {

    public static ArrayList<Chapter> getChapters(int bookId){
        //retorna todas os capitulos do livro com id = bookID


        ArrayList<Chapter> exercises = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            exercises.add(new Chapter(bookId,i,"nome"+i));
        }

        return exercises;
    }

    public static ArrayList<Exercise> getExercises(int chapterId){
        //return todos os exercicio do capitulo com id chapterId

        ArrayList<Exercise> exercises = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            exercises.add(new Exercise(0,i,"Resposta"+i));
        }

        return exercises;
    }

    public static ArrayList<Answer> getAnswers(int exerciseId){
        //retorna todas as repostas do exercicio com id exerciseId

        ArrayList<Answer> exercises = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            exercises.add(new Answer("questnion"+i,"resoistaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+i,i,i));
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


    public static int getExerciseId(int chapterId,int exerciseNumber){
        //retorna o id do exercicio exerciseNumber do capitlo com id = chapterId

        return 0;
    }

    public static int getChapterId(int bookId,int chapterNumber){
        return 0;
    }


}
