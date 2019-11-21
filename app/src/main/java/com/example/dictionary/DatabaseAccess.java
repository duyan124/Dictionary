package com.example.dictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.google.gson.Gson;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper1,openHelper2;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    public DatabaseAccess(Context context){
        this.openHelper1=new DatabaseOpenHelper(context);
        this.openHelper2=new DatabaseOpenHelper(context,"viet_anh.db");
    }

    public static DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open1(){
        this.database=openHelper1.getWritableDatabase();
    }

    public void close1(){
        if(database!=null){
            this.database.close();
        }
    }
    public void open2(){
        this.database=openHelper2.getWritableDatabase();
    }

    public void close2(){
        if(database!=null){
            this.database.close();
        }
    }
    public ArrayList<Vocabulary> getWords1(){
        ArrayList<Vocabulary> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("Select * from anh_viet",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Vocabulary vocabulary=new Vocabulary();
            vocabulary.setId(cursor.getInt(0));
            vocabulary.setWord(cursor.getString(1));
            list.add(vocabulary);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public ArrayList<Vocabulary> getWords2(){
        ArrayList<Vocabulary> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("Select * from viet_anh",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Vocabulary vocabulary=new Vocabulary();
            vocabulary.setId(cursor.getInt(0));
            vocabulary.setWord(cursor.getString(1));
            list.add(vocabulary);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public String getDefinition1(String word){
        String definition ="";
        Cursor cursor = database.rawQuery("Select * from anh_viet where word=?",new String[]{word});
        cursor.moveToFirst();
        definition =cursor.getString(2);
        cursor.close();
        return definition;
    }
    public String getDefinition2(String word){
        String definition="";
        Cursor cursor = database.rawQuery("Select * from viet_anh where word=?",new String[]{word});
        cursor.moveToFirst();
        definition=cursor.getString(2);
        cursor.close();
        return definition;
    }
    public void addWord(String w, String df){
        ContentValues values = new ContentValues();
        values.put("word",w);
        values.put("content",df);
        database.insert("anh_viet",null,values);
        database.close();
    }
}
