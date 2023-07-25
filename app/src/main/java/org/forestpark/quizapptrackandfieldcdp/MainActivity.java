package org.forestpark.quizapptrackandfieldcdp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView questionTV;
    Button falseBTN;
    Button trueBTN;
    Button scoreBTN;

    int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTV = (TextView) findViewById(R.id.questionTV);
        falseBTN = (Button) findViewById(R.id.falseBTN);
        trueBTN = (Button) findViewById(R.id.trueBTN);
        scoreBTN = (Button) findViewById(R.id.scoreBTN);
        score = 0;

        falseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = "Sorry, you are incorrect!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
            }
        });
        trueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                CharSequence text = "You are correct! Congrats!";
                int duration = Toast.LENGTH_SHORT;
                score++;
                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
            }
        });
        scoreBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent scoreIntent = new Intent(MainActivity.this, ScoreActivity.class);
              scoreIntent.putExtra("score", score);
              startActivity(scoreIntent);
            }
        });
    }
}