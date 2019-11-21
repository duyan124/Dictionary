package com.example.dictionary;

import java.io.Serializable;

public class Vocabulary implements Serializable {
    private int id;
    private String word;

    public Vocabulary(){

    }
    public Vocabulary(String word) {
        this.word = word;
    }
    public Vocabulary(int id,String word) {
        this.id=id;
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
