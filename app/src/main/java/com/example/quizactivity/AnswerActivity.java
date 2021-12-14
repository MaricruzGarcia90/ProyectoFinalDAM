package com.example.quizactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    private static final String KEY_QUESTION = "QUESTION";
    private static final String KEY_ANSWER = "ANSWER";
    private static final String KEY_ANSWER_SHOW = "ANSWER_SHOW";

    private TextView txtQuestion;
    private TextView txtAnswer;
    private Button btnShowAnswer;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        int value_question = getIntent().getIntExtra(KEY_QUESTION, 0);
        boolean value_answer = getIntent().getBooleanExtra(KEY_ANSWER, false);

        Log.d("VALORES", "valor_question:" + value_question);
        Log.d("VALORES", "valor_answer:" + value_answer);

        txtQuestion = findViewById(R.id.txtQuestionAnswer);
        txtAnswer = findViewById(R.id.txtShowAnswer);
        btnShowAnswer = findViewById(R.id.btnShowAnswer);
        btnBack = findViewById(R.id.btnBack);

        txtQuestion.setText(value_question);

        btnShowAnswer.setOnClickListener(view -> {
           checkAnswer(value_answer);
           Intent data = new Intent();
           data.putExtra(KEY_ANSWER_SHOW, true);
           setResult(RESULT_OK, data);
        });
        //txtAnswer.setText(value_answer);

        btnBack.setOnClickListener(view ->{
            finish();
        });


    }

    private void checkAnswer(boolean value_answer){


        if (value_answer){
            txtAnswer.setText(R.string.verdadero_answer);
        }
        else{
            txtAnswer.setText(R.string.falso_answer);
        }
    }


}