package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddWord extends AppCompatActivity {
    EditText edtW,edtD;
    ImageView imageView;
    DatabaseAccess db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        edtW = (EditText) findViewById(R.id.edt_word);
        edtD =(EditText) findViewById(R.id.edt_definition);
        imageView =(ImageView) findViewById(R.id.img_them);

        db = DatabaseAccess.getInstance(this);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = edtW.getText().toString();
                String definition = edtD.getText().toString();
                if(word.length()!=0 && definition.length()!=0) {
                    db.open1();
                    db.addWord(word,definition);
                    db.close1();
                    finish();
                }
            }
        });
    }
}
