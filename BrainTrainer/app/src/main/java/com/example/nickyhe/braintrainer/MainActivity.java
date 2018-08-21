package com.example.nickyhe.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> answers = new ArrayList<>();
    int positionOfCorrectAnswer;
    int score;
    int numOfQuestions;

    //define the layout
    private ConstraintLayout mainLayout;

    //define the TextViews
    private TextView titleTextView;
    private TextView questionTextView;
    private TextView resultTextView;
    private TextView markTextView;
    private TextView timerTextView;

    //define the buttons
    private Button startBtn;
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button playAgainBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the mainLayout
        mainLayout = findViewById(R.id.mainLayout);

        //initialize the TextViews
        titleTextView = findViewById(R.id.titleTextView);
        questionTextView = findViewById(R.id.questionTextView);
        resultTextView = findViewById(R.id.resultTextView);
        markTextView = findViewById(R.id.markTextView);
        timerTextView = findViewById(R.id.timerTextView);

        //initialize the buttons
        startBtn = findViewById(R.id.startBtn);
        btn0 = findViewById(R.id.button0);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        playAgainBtn = findViewById(R.id.playAgainBtn);

    }


    public void playAgain(View view) {
        score = 0;
        numOfQuestions = 0;

        //set proper contents to TextViews
        markTextView.setText("0/0");
        timerTextView.setText("30S");
        resultTextView.setText("");

        //set proper attributes to buttons
        btn0.setEnabled(true);
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        playAgainBtn.setVisibility(View.INVISIBLE);

        //initialize the question
        generateQuestion();

        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(millisUntilFinished / 1000 + "S");
            }

            @Override
            public void onFinish() {

                //set proper texts to views
                timerTextView.setText("0S");
                resultTextView.setText("Your score: " + score + "/" + numOfQuestions);

                //set proper functionality of buttons
                playAgainBtn.setVisibility(View.VISIBLE);
                btn0.setEnabled(false);
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
            }
        }.start();
    }

    public void start(View view) {

        startBtn.setVisibility(View.INVISIBLE);
        titleTextView.setVisibility(View.INVISIBLE);
        mainLayout.setVisibility(View.VISIBLE);

        //Start the game for first time
        playAgain(this.resultTextView);
    }

    public void compareAnswer(View view) {

        numOfQuestions++;

        if (view.getTag().toString().equals(String.valueOf(positionOfCorrectAnswer))) {
            resultTextView.setText("Correct!");
            score++;
        } else {
            resultTextView.setText("Wrong...");
        }

        markTextView.setText(score + "/" + numOfQuestions);
        generateQuestion();
    }

    public void generateQuestion() {

        Random rand = new Random();
        int a = rand.nextInt(51);
        int b = rand.nextInt(51);

        questionTextView.setText(a + "+" + b);
        positionOfCorrectAnswer = rand.nextInt(4);
        int wrongAnswer;

        //Clear the answers before adding new answers
        answers.clear();

        for (int i = 0; i < 4; i++) {
            if (i == positionOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                wrongAnswer = rand.nextInt(101);

                while (wrongAnswer == a + b) {
                    wrongAnswer = rand.nextInt(101);
                }

                answers.add(wrongAnswer);
            }
        }

        btn0.setText(answers.get(0).toString());
        btn1.setText(answers.get(1).toString());
        btn2.setText(answers.get(2).toString());
        btn3.setText(answers.get(3).toString());
    }
}
