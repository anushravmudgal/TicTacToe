package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {
int actplay = 0;

boolean drawstate = true;

   int gamestate[] = {2,2,2,2,2,2,2,2,2};

   int wincomb[][]={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    boolean actgame = true;

    public void dropIn(View view) {
        int counter = Integer.parseInt(view.getTag().toString());
        if (gamestate[counter] == 2 && actgame) {
            if (actplay == 0) {
                ImageView coin = (ImageView) view;
                TextView pl1 = findViewById(R.id.pl1);
                TextView pl2 = findViewById(R.id.pl2);
                coin.setTranslationY(-1000f);
                coin.setImageResource(R.drawable.red);
                coin.animate().translationYBy(1000f).rotation(720f).setDuration(500);
                actplay++;
                pl1.setAlpha(0.2f);
                pl2.setAlpha(1f);
                gamestate[counter] = 0;
            } else {
                ImageView coin = (ImageView) view;
                TextView pl1 = findViewById(R.id.pl1);
                TextView pl2 = findViewById(R.id.pl2);
                coin.setTranslationY(-1000f);
                coin.setImageResource(R.drawable.yellow);
                coin.animate().translationYBy(1000f).rotation(720f).setDuration(500);
                actplay--;
                gamestate[counter] = 1;
                pl1.setAlpha(1f);
                pl2.setAlpha(0.2f);

            }


            for (int winco[] : wincomb) {
                if (gamestate[winco[0]] == gamestate[winco[1]] && gamestate[winco[1]] == gamestate[winco[2]] && gamestate[winco[0]] != 2) {
                    TextView win = findViewById(R.id.textView2);
                    TextView pl1 = findViewById(R.id.pl1);
                    TextView pl2 = findViewById(R.id.pl2);
                    int play1 = Integer.parseInt(pl1.getText().toString());
                    int play2 = Integer.parseInt(pl2.getText().toString());

                    String winner = "";
                    LinearLayout res = (LinearLayout) findViewById(R.id.result);
                    res.setScaleX(0f);
                    res.setVisibility(View.VISIBLE);

                    res.animate().scaleXBy(1f).rotation(360f).setDuration(800);

                    MediaPlayer mp = MediaPlayer.create(this, R.raw.tada);
                    mp.start();

                    actgame = false;
                    drawstate = false;

                    if (gamestate[winco[0]] == 0) {
                        winner = "Player 1 Wins!!";
                        play1++;

                    } else {
                        winner = "Player 2 Wins!!";
                        play2++;
                    }
                    win.setText(winner);
                    pl1.setText("" + play1);
                    pl2.setText("" + play2);
                }

            }
            int flag = 9;
            for (int games : gamestate) {
                if (games != 2) {

                    flag--;
                }
            }
            if (flag == 0 && drawstate) {


                TextView win = findViewById(R.id.textView2);
                TextView pl1 = findViewById(R.id.pl1);
                TextView pl2 = findViewById(R.id.pl2);
                int play1 = Integer.parseInt(pl1.getText().toString());
                int play2 = Integer.parseInt(pl2.getText().toString());


                LinearLayout res = (LinearLayout) findViewById(R.id.result);
                res.setScaleX(0f);
                res.setVisibility(View.VISIBLE);

                actgame = false;

                res.animate().scaleXBy(1f).rotation(360f).setDuration(800);

                win.setText("Draw!!!");
                pl1.setText("" + play1);
                pl2.setText("" + play2);
            }


        }


    }




    public void again(View view){
        LinearLayout res = (LinearLayout) findViewById(R.id.result);
        res.setVisibility(View.INVISIBLE);
        res.setScaleX(0f);
        res.setRotation(0f);
        TextView pl1 = findViewById(R.id.pl1);
        TextView pl2 = findViewById(R.id.pl2);
        pl1.setAlpha(1f);
        pl2.setAlpha(0.2f);

        Log.i("CHK", "before reset  ");
        actplay = 0;
        drawstate = true;
        actgame = true;


        for(int i=0; i<gamestate.length;i++){

            gamestate[i]=2;

            Log.i("CHK", "LOOP  "+i);

        }

        Log.i("CHK", "before grid ");

        androidx.gridlayout.widget.GridLayout gd;
        gd = findViewById(R.id.gd);
        Log.i("CHIlDCoNT", "child count  "+ gd.getChildCount());
     for(int j =0; j < gd.getChildCount();j++){
            Log.i("GridCHK", "Child Cont = "+j);
            ((ImageView)gd.getChildAt(j)).setImageResource(0);

        }

    }

    public void reset(View view){
        LinearLayout res = (LinearLayout) findViewById(R.id.result);
        res.setVisibility(View.INVISIBLE);
        TextView pl1 = findViewById(R.id.pl1);
        TextView pl2 = findViewById(R.id.pl2);
        pl1.setText("0");
        pl2.setText("0");
        pl1.setAlpha(1f);
        pl2.setAlpha(0.2f);
        res.setScaleX(0f);
        res.setRotation(0f);

        drawstate = true;
        actgame = true;
        actplay = 0;
        for(int i=0; i<gamestate.length;i++){

            gamestate[i]=2;



        }



        androidx.gridlayout.widget.GridLayout gd;
        gd = findViewById(R.id.gd);

        for(int j =0; j < gd.getChildCount();j++){

            ((ImageView)gd.getChildAt(j)).setImageResource(0);

        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
