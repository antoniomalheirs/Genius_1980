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

public class telajogo extends AppCompatActivity
{

    Button btniniciar,btnred, btnblue, btnyellow, btngreen, btnreset;
    TextView view,highscore;

    int score=0, state=0, multi, highScore=0,scoree;
    int [] jogo;

    private SecureRandom secureRandom = new SecureRandom();
    private SharedPreferences sharedPreferences;
    private static final String PREF_SCORE = "pref_score", PREF_HIGH_SCORE = "pref_highscore";

    boolean gameover = false, start=false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telajogo);

        btniniciar = findViewById(R.id.btninicio);
        btnblue = findViewById(R.id.btnazul);
        btnred = findViewById(R.id.btnvermelho);
        btnreset = findViewById(R.id.btnreset);
        btngreen = findViewById(R.id.btnverde);
        btnyellow = findViewById(R.id.btnamarelo);
        view = findViewById(R.id.view);
        highscore = findViewById(R.id.pontumax);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        highScore = pontuacaoMax();
        scoree = sharedPreferences.getInt(PREF_SCORE, 0);

        view.setText("Pontuação: " + score);
        highscore.setText("Maior pontuação: " + highScore);

        btnreset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (state >= 1 && gameover == true)
                {
                    Intent intent = new Intent(getApplicationContext(), telajogo.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnred.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (state >= 1)
                {
                    jogadaplayer(0);
                }

                Handler antibug = new Handler();
                antibug.postDelayed(new Runnable()
                {
                    @Override
                    public void run() {
                        btnblue.setClickable(true);
                    }
                }, 1000);
            }
        });

        btngreen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (state >= 1)
                {
                    jogadaplayer(1);
                }

                Handler antibug = new Handler();
                antibug.postDelayed(new Runnable()
                {
                    @Override
                    public void run() {
                        btnblue.setClickable(true);
                    }
                }, 1000);
            }
        });

        btnblue.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (state >= 1)
                {
                    jogadaplayer(2);
                }

                Handler antibug = new Handler();
                antibug.postDelayed(new Runnable()
                {
                    @Override
                    public void run() {
                        btnblue.setClickable(true);
                    }
                }, 1000);
            }
        });

        btnyellow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (state >= 1)
                {
                    jogadaplayer(3);
                }

                Handler antibug = new Handler();
                antibug.postDelayed(new Runnable()
                {
                    @Override
                    public void run() {
                        btnblue.setClickable(true);
                    }
                }, 1000);
            }
        });

        btniniciar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (gameover == false && start==false && state==0)
                {
                    state++;
                    start=true;
                    jogo = gerarSequencia();

                    blinkcores(btnred,btnblue,btngreen,btnyellow);

                    Handler retornaclick = new Handler();
                    retornaclick.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnred.setClickable(true);
                            btnblue.setClickable(true);
                            btnyellow.setClickable(true);
                            btngreen.setClickable(true);
                        }
                    }, multi);
                }
                else if (gameover == false && start==false && state >= 1)
                {
                    Handler comeca = new Handler();
                    comeca.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            adicionarValor(secureRandom.nextInt(4));

                            blinkcores(btnred,btnblue,btngreen,btnyellow);

                            Handler retornaclick = new Handler();
                            retornaclick.postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    btnred.setClickable(true);
                                    btnblue.setClickable(true);
                                    btnyellow.setClickable(true);
                                    btngreen.setClickable(true);
                                }
                            }, multi);
                        }
                    }, 200);
                }
            }
        });
    }

    public int[] gerarSequencia()
    {
        jogo = new int[1];
        jogo[0] = secureRandom.nextInt(4);
        return jogo;
    }

    public void adicionarValor(int valor)
    {
        if (state <= 50)
        {
            state++;
            int[] novaSequencia = new int[state];
            for (int i = 0; i < jogo.length; i++)
            {
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

    private int pontuacaoMax()
    {
        return sharedPreferences.getInt(PREF_HIGH_SCORE, 0);
    }

    private void salvaPontuacao(int score)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(PREF_SCORE, score);
        editor.apply();

        int highScore = pontuacaoMax();

        if (score > highScore)
        {
            editor.putInt(PREF_HIGH_SCORE, score);
            editor.apply();
        }
    }

    private void blinkcores(Button red, Button blue, Button green, Button yellow)
    {
        btnred.setClickable(false);
        btnblue.setClickable(false);
        btnyellow.setClickable(false);
        btngreen.setClickable(false);

        Handler blinkcores = new Handler();
        for (int i = 0; i < jogo.length; i++)
        {
            int fseq = i;
            blinkcores.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
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
                    Handler voltapadrao = new Handler();
                    voltapadrao.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
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

    public void jogadaplayer(int btnId)
    {
        if (!gameover && btnId >= 0 && btnId <= 3)
        {
            if (btnId == jogo[score])
            {
                score++;
                if (score == jogo.length)
                {
                    score = 0;
                    adicionarValor(secureRandom.nextInt(4));
                    blinkcores(btnred, btnblue, btngreen, btnyellow);
                    Handler retornaclick = new Handler();
                    retornaclick.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            btnred.setClickable(true);
                            btnblue.setClickable(true);
                            btngreen.setClickable(true);
                            btnyellow.setClickable(true);
                        }
                    }, multi);
                }
            }
            else
            {
                gameover = true;
                btnred.setClickable(false);
                btnblue.setClickable(false);
                btngreen.setClickable(false);
                btnyellow.setClickable(false);
                salvaPontuacao(state);
                Toast.makeText(getApplicationContext(), "GAME OVER! " + score + " pontos.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}