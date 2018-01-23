package com.tictactoe;


import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class StartGame extends AppCompatActivity  {


    public static final  String SAVE="Mysave";
    public static String playername1,playername2;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,btn_save,btn_load,btn_new_game,mute,winner;
    TextView player1,player2,score_player1,score_player2,player1_score,player2_score,choose1,choose2;
     String player1_choice,player2_choice;
    int turn=0;
    int score_player_1,score_player_2;
    static String button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MediaPlayer sound;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        getSupportActionBar().hide();
        initialize();
        SelectSide();
        LogicGame();
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
        btn_new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
                play();
            }
        });
       mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });
        winner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent winner=new Intent(getApplicationContext(),winnertable.class);
                winner.putExtra("Player1name",player1.getText().toString());
                winner.putExtra("Player2name",player2.getText().toString());
                winner.putExtra("ScorePlayer1",score_player1.getText().toString());
                winner.putExtra("ScorePlayer2",score_player2.getText().toString());
                startActivity(winner);
            }
        });

    }



    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public  void onRestart(){
        super.onRestart();
        play();
    }



    public void play()
    {
        if(sound==null)
        {
            sound=MediaPlayer.create(this,R.raw.nightcorecenturies);
            sound.start();
        }



    }



    public void stop()
    {
        sound.release();
        sound=null;
    }



    public String Lottery() {
        String messageToPlayer;
        Random r = new Random();
        int num;
        num = r.nextInt(1)*2;

        if (num == 0) {

            Toast.makeText(StartGame.this, player1.getText().toString() + " your turn ", Toast.LENGTH_LONG).show();
            messageToPlayer = "Player1";

        } else  {
            Toast.makeText(StartGame.this, player2.getText().toString() + " your turn", Toast.LENGTH_LONG).show();
            messageToPlayer = "Player2";

        }
        return messageToPlayer;
    }


    public void SelectSide() {
        String lottery=Lottery();
        Random r=new Random();
        int num;
        num=r.nextInt(2);
        if (lottery.equalsIgnoreCase("Player1")) {
            turn=1;

            if (num==0) {
                player1_choice = "X";
                choose1.setText(player1_choice);
                player2_choice="O";
                choose2.setText(player2_choice);

            }
            else {
                player1_choice = "O";
                choose1.setText(player1_choice);
                player2_choice="X";
                choose2.setText(player2_choice);



            }
        } else  if (lottery.equalsIgnoreCase("Player2")) {
            turn=2;
            if (num==0) {
                player2_choice = "X";
                choose2.setText(player2_choice);
                player1_choice="O";
                choose1.setText(player1_choice);

            }
            else {

                player2_choice = "O";
                choose2.setText(player2_choice);
                player1_choice="X";
                choose1.setText(player1_choice);


            }


        }

    }



    public void initialize() {
        playername1 = getIntent().getExtras().getString("player1");
        playername2 = getIntent().getExtras().getString("player2");
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        winner = (Button) findViewById(R.id.winner);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_load = (Button) findViewById(R.id.btn_load);
        btn_new_game = (Button) findViewById(R.id.btn_new_game);
        mute = (Button) findViewById(R.id.mute);
        player1 = (TextView) findViewById(R.id.player1);
        player2 = (TextView) findViewById(R.id.player2);
        score_player1 = (TextView) findViewById(R.id.score1);
        score_player2 = (TextView) findViewById(R.id.score_2);
        player1_score = (TextView) findViewById(R.id.player1_score);
        player2_score = (TextView) findViewById(R.id.player2_score);
        choose1 = (TextView) findViewById(R.id.choose1);
        choose2 = (TextView) findViewById(R.id.choose2);
        choose1.setText(player1_choice);
        choose2.setText(player2_choice);
        player1.setText(playername1);
        player2.setText(playername2);
        player1_score.setText(playername1);
        player2_score.setText(playername2);
        score_player_1 = 0;
        score_player_2 = 0;
        score_player1.setText(String.valueOf(score_player_1));
        score_player2.setText(String.valueOf(score_player_2));
        player1.setText(playername1);
        player2.setText(playername2);
        play();
    }














    public void LogicGame() {

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        if (btn1.getText().toString().equals("")) {
                            if (turn == 1) {
                                turn = 2;
                                btn1.setText(player1_choice);


                            } else if (turn == 2) {
                                turn = 1;
                                btn1.setText(player2_choice);

                            }

                        }
                        GameOver();
                    }


                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (btn2.getText().toString().equals("")) {
                            if (turn == 1) {
                                turn = 2;
                                btn2.setText(player1_choice);


                            } else if (turn == 2) {
                                turn = 1;
                                btn2.setText(player2_choice);

                            }


                        }
                        GameOver();

                    }
                });
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (btn3.getText().toString().equals("")) {
                            if (turn == 1) {
                                turn = 2;
                                btn3.setText(player1_choice);


                            } else if (turn == 2) {
                                turn = 1;
                                btn3.setText(player2_choice);

                            }

                        }
                        GameOver();


                    }
                });
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (btn4.getText().toString().equals("")) {
                            if (turn == 1) {
                                turn = 2;
                                btn4.setText(player1_choice);


                            } else if (turn == 2) {
                                turn = 1;
                                btn4.setText(player2_choice);

                            }


                        }

                        GameOver();

                    }
                });

                btn5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (btn5.getText().toString().equals("")) {

                            if (turn == 1) {
                                turn = 2;
                                btn5.setText(player1_choice);


                            } else if (turn == 2) {
                                turn = 1;
                                btn5.setText(player2_choice);

                            }

                        }

                        GameOver();


                    }
                });
                btn6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (btn6.getText().toString().equals("")) {

                            if (turn == 1) {
                                turn = 2;
                                btn6.setText(player1_choice);


                            } else if (turn == 2) {
                                turn = 1;
                                btn6.setText(player2_choice);

                            }


                        }

                        GameOver();

                    }
                });
                btn7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (btn7.getText().toString().equals("")) {
                            if (turn == 1) {
                                turn = 2;
                                btn7.setText(player1_choice);


                            } else if (turn == 2) {
                                turn = 1;
                                btn7.setText(player2_choice);

                            }

                        }

                        GameOver();


                    }
                });
                btn8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (btn8.getText().toString().equals("")) {
                            if (turn == 1) {
                                turn = 2;
                                btn8.setText(player1_choice);


                            } else if (turn == 2) {
                                turn = 1;
                                btn8.setText(player2_choice);

                            }

                        }
                        GameOver();

                    }
                });
                btn9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (btn9.getText().toString().equals("")) {
                            if (turn == 1) {
                                turn = 2;
                                btn9.setText(player1_choice);


                            } else if (turn == 2) {
                                turn = 1;
                                btn9.setText(player2_choice);

                            }

                        }

                        GameOver();


                    }
                });
            }

            public void GameOver() {

                boolean end = false;
                button1 = btn1.getText().toString();
                button2 = btn2.getText().toString();
                button3 = btn3.getText().toString();
                button4 = btn4.getText().toString();
                button5 = btn5.getText().toString();
                button6 = btn6.getText().toString();
                button7 = btn7.getText().toString();
                button8 = btn8.getText().toString();
                button9 = btn9.getText().toString();

                if (player1_choice.contains("X") && button1.equals("X") && button2.equals("X") && button3.equals("X")) {
                    Toast.makeText(StartGame.this, player1.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_1++;
                    score_player1.setText(Integer.toString(score_player_1));
                    end = true;
                    play();

                }
                if (player1_choice.contains("X") && button1.equals("X") && button4.equals("X") && button7.equals("X")) {
                    Toast.makeText(StartGame.this, player1.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_1++;
                    score_player1.setText(Integer.toString(score_player_1));
                    end = true;
                   play();
                }
                if (player1_choice.contains("X") && button1.equals("X") && button5.equals("X") && button9.equals("X")) {
                    Toast.makeText(StartGame.this, player1.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_1++;
                    score_player1.setText(Integer.toString(score_player_1));
                    end = true;
                   play();
                }
                if (player1_choice.contains("X") && button3.equals("X") && button5.equals("X") && button7.equals("X")) {
                    Toast.makeText(StartGame.this, player1.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_1++;
                    score_player1.setText(Integer.toString(score_player_1));
                    end = true;
                   play();
                }
                if (player1_choice.contains("X") && button4.equals("X") && button5.equals("X") && button6.equals("X")) {
                    Toast.makeText(StartGame.this, player1.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_1++;
                    score_player1.setText(Integer.toString(score_player_1));
                    end = true;
                  play();
                }
                if (player1_choice.contains("X") && button7.equals("X") && button8.equals("X") && button9.equals("X")) {
                    Toast.makeText(StartGame.this, player1.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_1++;
                    score_player1.setText(Integer.toString(score_player_1));
                    end = true;
                   play();
                }
                if (player1_choice.contains("X") && button6.equals("X") && button9.equals("X")) {
                    Toast.makeText(StartGame.this, player1.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_1++;
                    score_player1.setText(Integer.toString(score_player_1));
                    end = true;
                   play();
                }
                if (player1_choice.contains("X") && button2.equals("X") && button5.equals("X") && button8.equals("X")) {
                    Toast.makeText(StartGame.this, player1.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_1++;
                    score_player1.setText(Integer.toString(score_player_1));
                    end = true;
                  play();
                }


                if (player1_choice.contains("O") && button1.equals("O") && button2.equals("O") && button3.equals("O")) {
                    Toast.makeText(StartGame.this, player1.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_1++;
                    score_player1.setText(Integer.toString(score_player_1));
                    end = true;
                    play();
                }

                if (player1_choice.contains("O") && button1.equals("O") && button4.equals("O") && button7.equals("O")) {
                    Toast.makeText(StartGame.this, player1.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_1++;
                    score_player1.setText(Integer.toString(score_player_1));
                    end = true;
                    play();
                }

                if (player1_choice.contains("O") && button1.equals("O") && button5.equals("O") && button9.equals("O")) {
                    Toast.makeText(StartGame.this, player1.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_1++;
                    score_player1.setText(Integer.toString(score_player_1));
                    end = true;
                  play();
                }

                if (player1_choice.contains("O") && button3.equals("O") && button5.equals("O") && button7.equals("O")) {
                    Toast.makeText(StartGame.this, player1.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_1++;
                    score_player1.setText(Integer.toString(score_player_1));
                    end = true;
                  play();
                }

                if (player1_choice.contains("O") && button4.equals("O") && button5.equals("O") && button6.equals("O")) {
                    Toast.makeText(StartGame.this, player1.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_1++;
                    score_player1.setText(Integer.toString(score_player_1));
                    end = true;
                  play();
                }

                if (player1_choice.contains("O") && button7.equals("O") && button8.equals("O") && button9.equals("O")) {
                    Toast.makeText(StartGame.this, player1.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_1++;
                    score_player1.setText(Integer.toString(score_player_1));
                    end = true;
                  play();
                }

                if (player1_choice.contains("O") && button3.equals("O") && button6.equals("O") && button9.equals("O")) {
                    Toast.makeText(StartGame.this, player1.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_1++;
                    score_player1.setText(Integer.toString(score_player_1));
                    end = true;
                  play();
                }

                if (player1_choice.contains("O") && button2.equals("O") && button5.equals("O") && button8.equals("O")) {
                    Toast.makeText(StartGame.this, player1.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_1++;
                    score_player1.setText(Integer.toString(score_player_1));
                    end = true;
                    play();
                }


                ////////////////////////////////

                if (player2_choice.contains("X") && button1.equals("X") && button2.equals("X") && button3.equals("X")) {
                    Toast.makeText(StartGame.this, player2.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_2++;
                    score_player2.setText(Integer.toString(score_player_2));
                    end = true;
                    play();
                }
                if (player2_choice.contains("X") && button1.equals("X") && button4.equals("X") && button7.equals("X")) {
                    Toast.makeText(StartGame.this, player2.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_2++;
                    score_player2.setText(Integer.toString(score_player_2));
                    end = true;
                   play();
                }
                if (player2_choice.contains("X") && button1.equals("X") && button5.equals("X") && button9.equals("X")) {
                    Toast.makeText(StartGame.this, player2.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_2++;
                    score_player2.setText(Integer.toString(score_player_2));
                    end = true;
                   play();
                }
                if (player2_choice.contains("X") && button3.equals("X") && button5.equals("X") && button7.equals("X")) {
                    Toast.makeText(StartGame.this, player2.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_2++;
                    score_player2.setText(Integer.toString(score_player_2));
                    end = true;
                   play();
                }
                if (player2_choice.contains("X") && button4.equals("X") && button5.equals("X") && button6.equals("X")) {
                    Toast.makeText(StartGame.this, player2.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_2++;
                    score_player2.setText(Integer.toString(score_player_2));
                    end = true;
                    play();
                }
                if (player2_choice.contains("X") && button7.equals("X") && button8.equals("X") && button9.equals("X")) {
                    Toast.makeText(StartGame.this, player2.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_2++;
                    score_player2.setText(Integer.toString(score_player_2));
                    end = true;
                    play();
                }
                if (player2_choice.contains("X") && button3.equals("X") && button6.equals("X") && button9.equals("X")) {
                    Toast.makeText(StartGame.this, player2.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_2++;
                    score_player2.setText(Integer.toString(score_player_2));
                    end = true;
                   play();

                }
                if (player2_choice.contains("X") && button2.equals("X") && button5.equals("X") && button8.equals("X")) {
                    Toast.makeText(StartGame.this, player2.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_2++;
                    score_player2.setText(Integer.toString(score_player_2));
                    end = true;
                    play();
                }


                if (player2_choice.contains("O") && button1.equals("O") && button2.equals("O") && button3.equals("O")) {
                    Toast.makeText(StartGame.this, player2.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_2++;
                    score_player2.setText(Integer.toString(score_player_2));
                    end = true;
                   play();
                }

                if (player2_choice.contains("O") && button1.equals("O") && button4.equals("O") && button7.equals("O")) {
                    Toast.makeText(StartGame.this, player2.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_2++;
                    score_player2.setText(Integer.toString(score_player_2));
                    end = true;
                      play();
                }

                if (player2_choice.contains("O") && button1.equals("O") && button5.equals("O") && button9.equals("O")) {
                    Toast.makeText(StartGame.this, player2.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_2++;
                    score_player2.setText(Integer.toString(score_player_2));
                    end = true;
                 play();
                }

                if (player2_choice.contains("O") && button3.equals("O") && button5.equals("O") && button7.equals("O")) {
                    Toast.makeText(StartGame.this, player2.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_2++;
                    score_player2.setText(Integer.toString(score_player_2));
                    end = true;
                  play();
                }

                if (player2_choice.contains("O") && button4.equals("O") && button5.equals("O") && button6.equals("O")) {
                    Toast.makeText(StartGame.this, player2.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_2++;
                    score_player2.setText(Integer.toString(score_player_2));
                    end = true;
                  play();
                }

                if (player2_choice.contains("O") && button7.equals("O") && button8.equals("O") && button9.equals("O")) {
                    Toast.makeText(StartGame.this, player2.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_2++;
                    score_player2.setText(Integer.toString(score_player_2));
                    end = true;
                  play();
                }

                if (player2_choice.contains("O") && button3.equals("O") && button6.equals("O") && button9.equals("O")) {
                    Toast.makeText(StartGame.this, player2.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_2++;
                    score_player2.setText(Integer.toString(score_player_2));
                    end = true;
                    play();
                }

                if (player2_choice.contains("O") && button2.equals("O") && button5.equals("O") && button8.equals("O")) {
                    Toast.makeText(StartGame.this, player2.getText().toString() + " is Winner", Toast.LENGTH_LONG).show();
                    score_player_2++;
                    score_player2.setText(Integer.toString(score_player_2));
                    end = true;
                  play();
                }

                if (player1_choice.contains("X") && player2_choice.contains("O") && button1.equals("X") && button2.equals("X") && button3.equals("O") && button4.equals("O") && button5.equals("X") && button6.equals("X") && button7.equals("X") && button8.equals("O") && button9.equals("O")) {

                    Toast.makeText(StartGame.this, " is Tie!!", Toast.LENGTH_LONG).show();
                    end = true;
                   play();
                }

                if (player1_choice.contains("O") && player2_choice.contains("X") && button1.equals("X") && button2.equals("X") && button3.equals("O") && button4.equals("O") && button5.equals("X") && button6.equals("X") && button7.equals("X") && button8.equals("O") && button9.equals("O")) {

                    Toast.makeText(StartGame.this, " is Tie!!", Toast.LENGTH_LONG).show();
                    end = true;
                   play();
                }

                if (player1_choice.contains("X") && player2_choice.contains("O") && button1.equals("X") && button2.equals("X") && button3.equals("O") && button4.equals("O") && button5.equals("O") && button6.equals("X") && button7.equals("X") && button8.equals("X") && button9.equals("O")) {

                    Toast.makeText(StartGame.this, " is Tie!!", Toast.LENGTH_LONG).show();
                    end = true;
                 play();
                }
                if (player1_choice.contains("O") && player2_choice.contains("X") && button1.equals("X") && button2.equals("X") && button3.equals("O") && button4.equals("O") && button5.equals("O") && button6.equals("X") && button7.equals("X") && button8.equals("X") && button9.equals("O")) {

                    Toast.makeText(StartGame.this, " is Tie!!", Toast.LENGTH_LONG).show();
                    end = true;
                    play();
                }

                if (player1_choice.contains("X") && player2_choice.contains("O") && button1.equals("O") && button2.equals("X") && button3.equals("O") && button4.equals("X") && button5.equals("X") && button6.equals("O") && button7.equals("X") && button8.equals("O") && button9.equals("X")) {

                    Toast.makeText(StartGame.this, " is Tie!!", Toast.LENGTH_LONG).show();
                    end = true;
                    play();
                }
                if (player1_choice.contains("O") && player2_choice.contains("X") && button1.equals("O") && button2.equals("X") && button3.equals("O") && button4.equals("X") && button5.equals("X") && button6.equals("O") && button7.equals("X") && button8.equals("O") && button9.equals("X")) {

                    Toast.makeText(StartGame.this, " is Tie!!", Toast.LENGTH_LONG).show();
                    end = true;
                   play();
                }

                if (player1_choice.contains("X") && player2_choice.contains("O") && button1.equals("O") && button2.equals("X") && button3.equals("X") && button4.equals("X") && button5.equals("O") && button6.equals("O") && button7.equals("O") && button8.equals("X") && button9.equals("X")) {

                    Toast.makeText(StartGame.this, " is Tie!!", Toast.LENGTH_LONG).show();
                    end = true;
                   play();
                }

                if (player1_choice.contains("O") && player2_choice.contains("X") && button1.equals("O") && button2.equals("X") && button3.equals("X") && button4.equals("X") && button5.equals("O") && button6.equals("O") && button7.equals("O") && button8.equals("X") && button9.equals("X")) {

                    Toast.makeText(StartGame.this, " is Tie!!", Toast.LENGTH_LONG).show();
                    end = true;
                    play();
                }

                if (player1_choice.contains("X") && player2_choice.contains("O") && button1.equals("X") && button2.equals("O") && button3.equals("X") && button4.equals("O") && button5.equals("X") && button6.equals("X") && button7.equals("O") && button8.equals("X") && button9.equals("O")) {

                    Toast.makeText(StartGame.this, " is Tie!!", Toast.LENGTH_LONG).show();
                    end = true;
                  play();
                }

                if (player1_choice.contains("O") && player2_choice.contains("X") && button1.equals("X") && button2.equals("O") && button3.equals("X") && button4.equals("O") && button5.equals("X") && button6.equals("X") && button7.equals("O") && button8.equals("X") && button9.equals("O")) {

                    Toast.makeText(StartGame.this, " is Tie!!", Toast.LENGTH_LONG).show();
                    end = true;
                  play();
                }

                if (player1_choice.contains("X") && player2_choice.contains("O") && button1.equals("X") && button2.equals("O") && button3.equals("X") && button4.equals("X") && button5.equals("O") && button6.equals("X") && button7.equals("O") && button8.equals("X") && button9.equals("O")) {

                    Toast.makeText(StartGame.this, " is Tie!!", Toast.LENGTH_LONG).show();
                    end = true;
                    play();
                }

                if (player1_choice.contains("O") && player2_choice.contains("X") && button1.equals("X") && button2.equals("O") && button3.equals("X") && button4.equals("X") && button5.equals("O") && button6.equals("X") && button7.equals("O") && button8.equals("X") && button9.equals("O")) {

                    Toast.makeText(StartGame.this, " is Tie!!", Toast.LENGTH_LONG).show();
                    end = true;
                   play();
                }

                if (player1_choice.contains("X") && player2_choice.contains("O") && button1.equals("O") && button2.equals("X") && button3.equals("O") && button4.equals("O") && button5.equals("X") && button6.equals("X") && button7.equals("X") && button8.equals("O") && button9.equals("O")) {

                    Toast.makeText(StartGame.this, " is Tie!!", Toast.LENGTH_LONG).show();
                    end = true;
                  play();
                }

                if (player1_choice.contains("O") && player2_choice.contains("X") && button1.equals("O") && button2.equals("X") && button3.equals("O") && button4.equals("O") && button5.equals("X") && button6.equals("X") && button7.equals("X") && button8.equals("O") && button9.equals("O")) {

                    Toast.makeText(StartGame.this, " is Tie!!", Toast.LENGTH_LONG).show();
                    end = true;
                    play();
                }

                if (player1_choice.contains("X") && player2_choice.contains("O") && button1.equals("O") && button2.equals("X") && button3.equals("O") && button4.equals("X") && button5.equals("O") && button6.equals("O") && button7.equals("X") && button8.equals("O") && button9.equals("X")) {

                    Toast.makeText(StartGame.this, " is Tie!!", Toast.LENGTH_LONG).show();
                    end = true;
                   play();
                }
                if (player1_choice.contains("O") && player2_choice.contains("X") && button1.equals("O") && button2.equals("X") && button3.equals("O") && button4.equals("X") && button5.equals("O") && button6.equals("O") && button7.equals("X") && button8.equals("O") && button9.equals("X")) {

                    Toast.makeText(StartGame.this, " is Tie!!", Toast.LENGTH_LONG).show();
                    end = true;
                    play();
                }

                if (player1_choice.contains("X") && player2_choice.contains("O") && button1.equals("X") && button2.equals("O") && button3.equals("X") && button4.equals("X") && button5.equals("O") && button6.equals("O") && button7.equals("O") && button8.equals("X") && button9.equals("X")) {

                    Toast.makeText(StartGame.this, " is Tie!!", Toast.LENGTH_LONG).show();
                    end = true;
                      play();
                }

                if (player1_choice.contains("O") && player2_choice.contains("X") && button1.equals("O") && button2.equals("X") && button3.equals("O") && button4.equals("X") && button5.equals("O") && button6.equals("O") && button7.equals("X") && button8.equals("O") && button9.equals("X")) {

                    Toast.makeText(StartGame.this, " is Tie!!", Toast.LENGTH_LONG).show();
                    end = true;
                      play();
                }



                if (end) {
                    btn1.setEnabled(false);
                    btn2.setEnabled(false);
                    btn3.setEnabled(false);
                    btn4.setEnabled(false);
                    btn5.setEnabled(false);
                    btn6.setEnabled(false);
                    btn7.setEnabled(false);
                    btn8.setEnabled(false);
                    btn9.setEnabled(false);
                    Intent send=new Intent(StartGame.this,winnertable.class);
                    send.putExtra("Player1name",player1.getText().toString());
                    send.putExtra("Player2name",player2.getText().toString());
                    send.putExtra("ScorePlayer1",score_player1.getText().toString());
                    send.putExtra("ScorePlayer2",score_player2.getText().toString());
                    startActivity(send);
                    play();

                }


            }




    public  void saveData()
    {
        SharedPreferences save = getSharedPreferences(SAVE, MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        editor.putInt("saveScorePlayer1", score_player_1).putInt("saveScorePlayer2", score_player_2).putString("valueButton1", btn1.getText().toString())
                .putString("valueButton2", btn2.getText().toString()).putString("valueButton3", btn3.getText().toString()).putString("valueButton4", btn4.getText().toString())
                .putString("valueButton5", btn5.getText().toString()).putString("valueButton6", btn6.getText().toString()).putString("valueButton7", btn7.getText().toString())
                .putString("valueButton8", btn8.getText().toString()).putString("valueButton9", btn9.getText().toString())
                .putString("Playername1", player1.getText().toString()).putString("Playername2", player2.getText().toString())
                .putString("Player1_choice", player1_choice.toString()).putString("Player2_choice", player2_choice.toString())
                .putString("player1_score", player1_score.getText().toString()).putString("player2_score", player2_score.getText().toString());

        editor.apply();
        Toast.makeText(getApplicationContext(), "The game is saving successfully!!!", Toast.LENGTH_SHORT).show();
    }

    public void loadData()
    {
        SharedPreferences Load = getSharedPreferences(SAVE, MODE_PRIVATE);
        score_player_1 = Load.getInt("saveScorePlayer1", 0);
        score_player_2 = Load.getInt("saveScorePlayer2", 0);
        playername1 = Load.getString("Playername1", player1.getText().toString());
        playername2 = Load.getString("Playername2", player2.getText().toString());
        player1.setText(playername1);
        player2.setText(playername2);
        player1_choice = Load.getString("Player1_choice", player1_choice.toString());
        player2_choice = Load.getString("Player2_choice", player2_choice);
        choose1.setText(player1_choice);
        choose2.setText(player2_choice);
        player1_score.setText(playername1);
        player2_score.setText(playername2);
        button1 = Load.getString("valueButton1", btn1.getText().toString());
        button2 = Load.getString("valueButton2", btn2.getText().toString());
        button3 = Load.getString("valueButton3", btn3.getText().toString());
        button4 = Load.getString("valueButton4", btn4.getText().toString());
        button5 = Load.getString("valueButton5", btn5.getText().toString());
        button6 = Load.getString("valueButton6", btn6.getText().toString());
        button7 = Load.getString("valueButton7", btn7.getText().toString());
        button8 = Load.getString("valueButton8", btn8.getText().toString());
        button9 = Load.getString("valueButton9", btn9.getText().toString());
        score_player1.setText(String.valueOf(score_player_1));
        score_player2.setText(String.valueOf(score_player_2));
        btn1.setText(button1);
        btn2.setText(button2);
        btn3.setText(button3);
        btn4.setText(button4);
        btn5.setText(button5);
        btn6.setText(button6);
        btn7.setText(button7);
        btn8.setText(button8);
        btn9.setText(button9);
           play();
    }

    public void newGame()
    {
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");

        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        btn5.setEnabled(true);
        btn6.setEnabled(true);
        btn7.setEnabled(true);
        btn8.setEnabled(true);
        btn9.setEnabled(true);
        SelectSide();
        score_player1.setText(Integer.toString(score_player_1));
        score_player2.setText(Integer.toString(score_player_2));

    }

        }







