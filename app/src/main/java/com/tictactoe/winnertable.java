package com.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class winnertable extends AppCompatActivity {


    public static final  String SAVEData="Mysave";
    TextView name,score1,name2,score2,nameline,scrolline;
    String  player1name;
    String player2name;
    String scorePlayer1;
    String scorePlayer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winnertable);
        getSupportActionBar().hide();
        name=(TextView) findViewById(R.id.nam1);
        score1=(TextView) findViewById(R.id.score1);
        name2=(TextView) findViewById(R.id.name2);
        score2=(TextView) findViewById(R.id.score2);
        nameline=(TextView) findViewById(R.id.nameline) ;
        scrolline=(TextView) findViewById(R.id.scorline) ;

        nameline.setGravity(Gravity.BOTTOM);
        scrolline.setGravity(Gravity.BOTTOM);
           player1name=getIntent().getExtras().getString("Player1name");
         player2name=getIntent().getExtras().getString("Player2name");
         scorePlayer1=getIntent().getExtras().getString("ScorePlayer1");
         scorePlayer2=getIntent().getExtras().getString("ScorePlayer2");
        name.setText(" "+player1name +" ");
        name.setGravity(Gravity.CENTER);
        score1.setGravity(Gravity.CENTER);
        score1.setText(" "+ scorePlayer1+" ");
        name2.setText(" " + player2name + " ");
        name2.setGravity(Gravity.CENTER);
        score2.setGravity(Gravity.CENTER);
        score2.setText(" " + scorePlayer2 + " ");
    saveData();
        loadData();
    }


    public  void saveData()
    {
        SharedPreferences save = getSharedPreferences(SAVEData, MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
      editor.putString("Player1name",player1name).putString("Player2name",player2name).putString("ScorePlayer1",scorePlayer1)
              .putString("ScorePlayer2",scorePlayer2);
        editor.apply();

    }

    public void loadData()
    {
        SharedPreferences Load = getSharedPreferences(SAVEData, MODE_PRIVATE);
         player1name=Load.getString("Player1name",player1name);
         player2name=getIntent().getExtras().getString("Player2name",player2name);
         scorePlayer1=getIntent().getExtras().getString("ScorePlayer1",scorePlayer1);
         scorePlayer2=getIntent().getExtras().getString("ScorePlayer2",scorePlayer2);
        name.setText(" "+player1name +" ");
        score1.setText(" "+ scorePlayer1+" ");
        name2.setText(" " + player2name + " ");
        score2.setText(" " + scorePlayer2 + " ");
    }
}