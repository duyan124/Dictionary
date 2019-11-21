package com.example.dictionary;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper {

    private static final String DATABASE1="anh_viet.db";
    private static final int VERSION1= 1;

    private static final String DATABASE2="viet_anh.db";
    private  static final int VERSION2=1;

    public DatabaseOpenHelper (Context context1){
        super(context1,DATABASE1,null,VERSION1);
    }
    public DatabaseOpenHelper (Context context2, String DATABASE2){
        super(context2,DATABASE2,null,null,VERSION2);
    }
}
