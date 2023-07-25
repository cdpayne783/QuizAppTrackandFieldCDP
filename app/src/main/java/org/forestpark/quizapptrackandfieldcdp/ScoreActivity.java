package org.forestpark.quizapptrackandfieldcdp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    TextView scoreTV;
    int score;
    Button sendBTN;
    String subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreTV = (TextView) findViewById(R.id.scoreTV);
        sendBTN = (Button) findViewById(R.id.sendBTN);

        Intent incomingIntent = getIntent();
        score = incomingIntent.getIntExtra("score", 0);

        scoreTV.setText("SCORE = " + score);
        subject = "";

        sendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view)
            {
            subject = getString(R.id.subjectText);
            String body = "I jut got a score of" + score + "on the Track and Field App";
            composeEmail(subject, body);
            }
        });

    }
    private void composeEmail(String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}