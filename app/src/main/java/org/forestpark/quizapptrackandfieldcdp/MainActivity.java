package org.forestpark.quizapptrackandfieldcdp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;




public class MainActivity extends AppCompatActivity {
    
    private View parentView;
    private Switch themeSwitch;
    TextView themeTV, titleTV;
    private UserSettings settings;

    TextView questionTV;
    Button falseBTN;
    Button trueBTN;
    Button nextBTN;
    int score;
    Question q1, q2, q3, q4, q5, currentQ;
    Question[] questions;
    int currentIndex;
    String message;

    ImageView imageIC;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        settings = (UserSettings) getApplication();

        imageIC = (ImageView) findViewById(R.id.imageIcon);
        int[] images = {R.drawable.track1, R.drawable.track2, R.drawable.track3, R.drawable.track4, R.drawable.track5 };

        intiWidgets();
        loadSharedPreferences();
        initSwitchListener();
        questionTV = (TextView) findViewById(R.id.questionTV);
        falseBTN = (Button) findViewById(R.id.falseBTN);
        trueBTN = (Button) findViewById(R.id.trueBTN);
        nextBTN = (Button) findViewById(R.id.nextBTN);
        score = 0;
        q1 = new Question(getString(R.string.q1Text), true, R.raw.q1);
        q2 = new Question(getString(R.string.q2Text), false, R.raw.q2);
        q3 = new Question(getString(R.string.q3Text), true, R.raw.q3);
        q4 = new Question(getString(R.string.q4Text), true, R.raw.q4);
        q5 = new Question(getString(R.string.q5Text), false, R.raw.q5);
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
                    MediaPlayer music = MediaPlayer.create(MainActivity.this, R.raw.missed);
                    music.start();
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
                    MediaPlayer music = MediaPlayer.create(MainActivity.this, R.raw.missed);
                    music.start();
                }
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), message, duration);
                toast.show();
            }
        });
        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                currentIndex++;
                if(currentIndex <= 5)
                {
                    currentQ = questions[currentIndex];
                    questionTV.setText(currentQ.getqPrompt());
                    imageIC.setImageResource(images[currentIndex]);

                    MediaPlayer music = MediaPlayer.create(MainActivity.this, currentQ.getSound());
                    music.start();
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

    private void intiWidgets() {
        themeTV = findViewById(R.id.themeTV);
        titleTV = findViewById(R.id.titleTV);
        themeSwitch = findViewById(R.id.themeSwitch);
        parentView = findViewById(R.id.parentView);
    }

    private void loadSharedPreferences()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(UserSettings.PREFERENCES, MODE_PRIVATE);
        String theme = sharedPreferences.getString(UserSettings.CUSTOM_THEME, UserSettings.LIGHT_THEME);
        settings.setCustomTheme(theme);
        updateView();
    }

    private void initSwitchListener()
    {
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked)
            {
                if(checked)
                    settings.setCustomTheme(UserSettings.DARK_THEME);
                else
                    settings.setCustomTheme(UserSettings.LIGHT_THEME);

                SharedPreferences.Editor editor = getSharedPreferences(UserSettings.PREFERENCES, MODE_PRIVATE).edit();
                editor.putString(UserSettings.CUSTOM_THEME, settings.getCustomTheme());
                editor.apply();
                updateView();
            }
        });
    }

    private void updateView()
    {
        final int black = ContextCompat.getColor(this, R.color.black);
        final int white = ContextCompat.getColor(this, R.color.white);

        if(settings.getCustomTheme().equals(UserSettings.DARK_THEME))
        {
            titleTV.setTextColor(white);
            themeTV.setTextColor(white);
            themeTV.setText("Dark");
            parentView.setBackgroundColor(black);
            themeSwitch.setChecked(true);
        }
        else
        {
            titleTV.setTextColor(black);
            themeTV.setTextColor(black);
            themeTV.setText("Light");
            parentView.setBackgroundColor(white);
            themeSwitch.setChecked(false);
        }

    }





    }

