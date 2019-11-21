package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;


public class Definition1 extends AppCompatActivity {
    WebView webView;
    TextView tvWord;
    ImageView imgSpeak;
    TextToSpeech speech;
    FloatingActionButton btnLike,btnUnlike;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition1);

        webView= (WebView) findViewById(R.id.web);
        imgSpeak = (ImageView) findViewById(R.id.img_speak);
        tvWord = (TextView) findViewById(R.id.tv_word);
        btnLike = (FloatingActionButton) findViewById(R.id.btn_like);
        btnUnlike = (FloatingActionButton) findViewById(R.id.btn_unlike);

        btnLike.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                btnLike.setVisibility(View.GONE);
                btnUnlike.setVisibility(View.VISIBLE);
            }
        });
        btnUnlike.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                btnLike.setVisibility(View.VISIBLE);
                btnUnlike.setVisibility(View.GONE);
            }
        });
        Intent intent = getIntent();
        String a=intent.getStringExtra("df");
        webView.loadData(a, "text/html", "UTF-8");
        tvWord.setText(intent.getStringExtra("w"));

        speech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = speech.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    } else {
                        imgSpeak.setEnabled(true);
                    }
                } else {
                }
            }
        });
        imgSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = tvWord.getText().toString();
                speech.speak(text,TextToSpeech.QUEUE_ADD,null);
            }
        });
    }
}