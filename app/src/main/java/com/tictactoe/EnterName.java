package com.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EnterName extends AppCompatActivity {

    EditText playername1,playername2;
    Button game;
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);
        playername1= (EditText) findViewById(R.id.player1);
        playername2= (EditText) findViewById(R.id.player2);





        game= (Button) findViewById(R.id.start_btn);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((playername1.getText().toString().equals("")&&playername2.getText().toString().equals(""))||playername1.getText().toString().equals("")||playername2.getText().toString().equals(""))
                {
                    Toast.makeText(EnterName.this,"Please enter name!",Toast.LENGTH_SHORT).show();
                }
                else {
                    finish();
                    Intent name1 = new Intent(EnterName.this, StartGame.class);
                    name1.putExtra("player1", playername1.getText().toString());
                    name1.putExtra("player2", playername2.getText().toString());
                    startActivity(name1);

                }

            }
        });


    }
}
