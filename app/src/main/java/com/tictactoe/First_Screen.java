package com.tictactoe;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

public class First_Screen extends AppCompatActivity {

    ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_first__screen);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        showProgressBar();
    }

    public void showProgressBar()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<20; i++)
                {
                    progressStatus +=20;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            if(progressStatus == progressBar.getMax())
                            {
                                finish();
                                Intent intent = new Intent(First_Screen.this, MainActivity.class);
                                startActivity(intent);

                            }
                        }
                    });
                    try{
                        Thread.sleep(3000);
                    }catch (InterruptedException e)
                    {
                        e.toString();
                    }
                }
            }
        }).start();
    }
}

