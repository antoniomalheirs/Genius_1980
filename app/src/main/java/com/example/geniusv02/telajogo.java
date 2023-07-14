package com.example.geniusv02;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.security.SecureRandom;
import java.util.Arrays;
public class telajogo extends AppCompatActivity {
    Button btnvoltar,btnred, btnblue, btnyellow, btngreen, btnreset;
    TextView t,a;
    int score=0, state=0, multi;
    int [] jogo;
    private SecureRandom secureRandom = new SecureRandom();
    private boolean clickable = true;
    private SharedPreferences sharedPreferences;
    private static final String PREF_SCORE = "pref_score";

    boolean gameover = false, start=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telajogo);

        btnvoltar = findViewById(R.id.btninicio);
        btnblue = findViewById(R.id.btnazul);
        btnred = findViewById(R.id.btnvermelho);
        btnreset = findViewById(R.id.btnreset);
        btngreen = findViewById(R.id.btnverde);
        btnyellow = findViewById(R.id.btnamarelo);
        t = findViewById(R.id.view);

    }

}