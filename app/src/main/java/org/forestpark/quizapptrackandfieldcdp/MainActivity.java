package org.forestpark.quizapptrackandfieldcdp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView questionTV;
    Button falseBTN;
    Button trueBTN;
    Button nextBTN;
    int score;
    Question q1, q2, q3, q4, q5, currentQ;
    Question[] questions;
    int currentIndex;
    String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTV = (TextView) findViewById(R.id.questionTV);
        falseBTN = (Button) findViewById(R.id.falseBTN);
        trueBTN = (Button) findViewById(R.id.trueBTN);
        nextBTN = (Button) findViewById(R.id.nextBTN);
        score = 0;
        q1 = new Question(getString(R.string.q1Text), true);
        q2 = new Question(getString(R.string.q2Text), false);
        q3 = new Question(getString(R.string.q3Text), true);
        q4 = new Question(getString(R.string.q4Text), true);
        q5 = new Question(getString(R.string.q5Text), false);
        currentQ = q1;
        questions = new Question[] {q1, q2, q3, q4, q5};
        message = "";

        falseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentQ.getCorrectAnswer() == false)
                {
                 message = getString(R.string.rightMSG);
                 score++;
                    MediaPlayer music = MediaPlayer.create(MainActivity.this, R.raw.cheer);
                    music.start();
                }
                else
                {
                message = getString(R.string.wrongMSG);
                }


                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getApplicationContext(), message, duration);
                toast.show();
            }
        });
        trueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                if (currentQ.getCorrectAnswer() == true)
                {
                    message = getString(R.string.rightMSG);
                    score++;
                    MediaPlayer music = MediaPlayer.create(MainActivity.this, R.raw.cheer);
                    music.start();
                }
                else
                {
                    message = getString(R.string.wrongMSG);
                }
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), message, duration);
                toast.show();
            }
        });
        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              currentIndex++;
              if(currentIndex <= 5)
              {
                  currentQ = questions[currentIndex];
                  questionTV.setText(currentQ.getqPrompt());
              }
              else
              {
                  Intent scoreIntent = new Intent(MainActivity.this, ScoreActivity.class);
                  scoreIntent.putExtra("score", score);
                  startActivity(scoreIntent);
              }
            }
        });
    }
}