package com.example.nishant.braintrainer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button playAgain;
    TextView check;
    TextView ques;
    TextView counter;
    TextView score;
    GridLayout grid;
    int scor,qa;
    int ans=0;
void generate()
{
    Random r=new Random();
    int x=r.nextInt(1000);
    int y=r.nextInt(1000);
    ques.setText(Integer.toString(x) + "+" + Integer.toString(y) + "=");
    for(int i=0;i<=3;i++)
    {
        Button b=(Button)grid.getChildAt(i);
        int n=r.nextInt(1000);
        b.setText(Integer.toString(n));
    }
    Button b=(Button)grid.getChildAt(r.nextInt(4));
    b.setText(Integer.toString(x+y));
    ans=x+y;
}
public void fun(View v)
{
    qa++;
    Button b=(Button)v;
    if(Integer.parseInt(b.getText().toString())==ans)
    {
        scor++;
        check.setText("CORRECT");
    }
    else
        check.setText("WRONG");
    String s=Integer.toString(scor) + "/" + Integer.toString(qa);
    score.setText(s);
    generate();
}
void fun1()
{
    playAgain.setVisibility(View.GONE);
    check.setText("");
    for(int i=0;i<grid.getChildCount();i++)
    {
        ((Button)grid.getChildAt(i)).setClickable(true);
    }
    scor=0;
    qa=0;

    new CountDownTimer(30000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            String s=Long.toString(millisUntilFinished/1000);
            counter.setText(s);
        }

        @Override
        public void onFinish() {

            MediaPlayer mp = MediaPlayer.create(getBaseContext(), (R.raw.ah));
            mp.start();

            for(int i=0;i<grid.getChildCount();i++)
            {
                ((Button)grid.getChildAt(i)).setClickable(false);
            }
            playAgain.setVisibility(View.VISIBLE);
            check.setText("YOUR SCORE IS " + Integer.toString(scor) + "/" + Integer.toString(qa));

        }
    }.start();
    score.setText("0/0");

    generate();

}
    public void go(View v)
    {

        Button b=(Button) v;
        b.setVisibility(View.GONE);
        grid.setVisibility(View.VISIBLE);
        check.setVisibility(View.VISIBLE);
        counter.setVisibility(View.VISIBLE);
        ques.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);

        fun1();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid=findViewById(R.id.gridLayout);
        check=findViewById(R.id.check);
        counter=findViewById(R.id.counter);
        ques=findViewById(R.id.ques);
        score=findViewById(R.id.score);
        playAgain=findViewById(R.id.playAgain);

    }
}
