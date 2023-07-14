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
    TextView view;

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
        view = findViewById(R.id.view);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        int score = sharedPreferences.getInt(PREF_SCORE, 0);

        view.setText("Pontuação: " + score);

        btnreset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            }
        });
        btnred.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            }
        });
        btngreen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            }
        });
        btnblue.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            }
        });
        btnyellow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

            }
        });
        btnvoltar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public int[] gerarSequencia()
    {
        jogo = new int[1];
        jogo[0] = secureRandom.nextInt(4);
        return jogo;
    }

    public void adicionarValor(int valor, TextView v)
    {
        if (state <= 50)
        {
            state++;
            int[] novaSequencia = new int[state];
            for (int i = 0; i < jogo.length; i++) {
                novaSequencia[i] = jogo[i];
            }
            novaSequencia[(jogo.length)] = valor;
            jogo = new int[state];
            jogo = novaSequencia;
        }
        else
        {
            gameover = true;
        }
    }

    private void salvandoPreferences(int score) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREF_SCORE, score);
        editor.apply();

        TextView view = findViewById(R.id.view);
        view.setText("Pontuação: " + score);
    }

    private void blinkcores(Button red, Button blue, Button green, Button yellow)
    {
        btnred.setClickable(false);
        btnblue.setClickable(false);
        btnyellow.setClickable(false);
        btngreen.setClickable(false);

        Handler pausablink = new Handler();
        for (int i = 0; i < jogo.length; i++)
        {
            int fseq = i;
            pausablink.postDelayed(new Runnable()
            {
                @Override
                public void run() {
                    switch (jogo[fseq])
                    {
                        case 0:
                            red.setBackgroundColor(Color.rgb(255,169,169));
                            red.setPressed(true);
                            break;
                        case 1:
                            green.setBackgroundColor(Color.rgb(169,255,169));
                            green.setPressed(true);
                            break;
                        case 2:
                            blue.setBackgroundColor(Color.rgb(169,169,255));
                            blue.setPressed(true);
                            break;
                        case 3:
                            yellow.setBackgroundColor(Color.rgb(255,255,169));
                            yellow.setPressed(true);
                            break;
                    }
                    Handler setblinkdefault = new Handler();
                    setblinkdefault.postDelayed(new Runnable()
                    {
                        @Override
                        public void run() {
                            yellow.setBackgroundColor(Color.rgb(255,255,0));
                            blue.setBackgroundColor(Color.rgb(0,0,255));
                            green.setBackgroundColor(Color.rgb(0,255,0));
                            red.setBackgroundColor(Color.rgb(255,0,0));
                            red.setPressed(false);
                            green.setPressed(false);
                            blue.setPressed(false);
                            yellow.setPressed(false);
                        }
                    }, 1000);
                }
            }, i * 1300);
            multi = i * 1600;
        }
    }
}