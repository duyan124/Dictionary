package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Definition2 extends AppCompatActivity {
    WebView webview;
    TextView tvWord2;
    FloatingActionButton btnLike2,btnUnlike2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition2);

        webview= (WebView) findViewById(R.id.wv);
        tvWord2 = (TextView) findViewById(R.id.tv_word2);

        Intent intent=getIntent();
        String a=intent.getStringExtra("dn");
        webview.loadData(a,"text/html","UTF-8");
        tvWord2.setText(intent.getStringExtra("tu"));

        btnLike2 = (FloatingActionButton) findViewById(R.id.btn_like2);
        btnUnlike2 = (FloatingActionButton) findViewById(R.id.btn_unlike2);

        btnLike2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                btnLike2.setVisibility(View.GONE);
                btnUnlike2.setVisibility(View.VISIBLE);
            }
        });
        btnUnlike2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                btnLike2.setVisibility(View.VISIBLE);
                btnUnlike2.setVisibility(View.GONE);
            }
        });
    }
}
