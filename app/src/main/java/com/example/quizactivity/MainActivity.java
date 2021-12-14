package com.example.quizactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class MainActivity<msgRId> extends AppCompatActivity {

    private Button btnTrue;
    private Button btnFalse;
    private Button btnNext;
    private Button btnAnswer;
    private TextView txtQuestion;


    private Question[] listQuestion = new Question[]{
            new Question(R.string.question_text1, true),
            new Question(R.string.question_text2, false),
            new Question(R.string.question_text3, false),
            new Question(R.string.question_text4, true),
            new Question(R.string.question_text5, true),
            new Question(R.string.question_text6, true),
            new Question(R.string.question_text7, false),
            new Question(R.string.question_text8, true),
            new Question(R.string.question_text9, true),
            new Question(R.string.question_text10, false),


    };

    private int currentIndex = 0;
    private static final int REQUEST_CODE_ANSWER = 0;
    private static final String KEY_INDEX = "index";
    private static final String KEY_QUESTION = "QUESTION";
    private static final String KEY_ANSWER = "ANSWER";
    private static final String KEY_ANSWER_SHOW = "ANSWER_SHOW";

    private boolean trampa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }


        btnTrue = findViewById(R.id.btnTrue);
        btnFalse = findViewById(R.id.btnFalse);
        btnNext = findViewById(R.id.btnNext);
        btnAnswer = findViewById(R.id.btnAnswer);

        txtQuestion = findViewById(R.id.txtQuestion);
        txtQuestion.setText(listQuestion[currentIndex].getTxtResId());

        btnAnswer.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
            intent.putExtra(KEY_QUESTION, listQuestion[currentIndex].getTxtResId());
            intent.putExtra(KEY_ANSWER, listQuestion[currentIndex].isAnswer());
            startActivityForResult(intent, REQUEST_CODE_ANSWER);
            //startActivity(intent);
        });

        btnTrue.setOnClickListener(view -> {
            checkAnswer(true);
        });

        btnFalse.setOnClickListener(view -> {
            checkAnswer(false);
        });

        btnNext.setOnClickListener(view -> {
            currentIndex = (currentIndex + 1) % listQuestion.length;
            if (currentIndex >= 9) {
                endquiz();

            } else {
                updateQuestion();
                trampa = false;
            }
        });
    }

    private void endquiz() {
        txtQuestion.setVisibility(View.GONE);
        btnTrue.setVisibility(View.GONE);
        btnFalse.setVisibility(View.GONE);
        btnNext.setVisibility(View.GONE);
        btnAnswer.setVisibility(View.GONE);
        Toast toast = Toast.makeText(this, "Ha terminado el juego", Toast.LENGTH_SHORT);
        toast.show();


    }

    private void resultados(){


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ANSWER) {
            if (data != null) {
                trampa = data.getBooleanExtra(KEY_ANSWER_SHOW, false);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        savedState.putInt(KEY_INDEX, currentIndex);

    }

    private void updateQuestion() {
        int question = listQuestion[currentIndex].getTxtResId();
        txtQuestion.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answer = listQuestion[currentIndex].isAnswer();
        int correctas = 0;
         int incorrectas = 0;
        int msgRId=0;


        if (trampa){
            msgRId = R.string.trampa_msg;
        }
        else if (userPressedTrue == answer){
            msgRId = R.string.true_msg;
             correctas ++;
        }
        else{
            msgRId = R.string.false_msg;
             incorrectas ++;
        }
        Toast.makeText(this, msgRId, Toast.LENGTH_SHORT).show();

        String resultado =
                String.format(Locale.getDefault(), "Correctas: %d -- Incorrectas: %d", correctas, incorrectas);

        Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();


    }

}