package com.example.supermegagigaquizz.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.supermegagigaquizz.R;
import com.example.supermegagigaquizz.model.model.Question;
import com.example.supermegagigaquizz.model.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
//ici on initialise les attributs//
    private TextView mQuestionTextView;
    private Button mAnswerOneButton;
    private Button mAnswerTwoButton;
    private Button mAnswerThreeButton;
    private Button mAnswerFourButton;
    QuestionBank mQuestionBank = generateQuestion();
    private int mRemainingQuestionCount;
    private Question mCurrentQuestion;
    private int mScore;
    public static final  String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //ici oin initialise les valeurs des attributs//

        mQuestionTextView = findViewById(R.id.game_activity_textview_question);
        mAnswerOneButton = findViewById(R.id.game_activity_button_1);
        mAnswerTwoButton = findViewById(R.id.game_activity_button_2);
        mAnswerThreeButton = findViewById(R.id.game_activity_button_3);
        mAnswerFourButton = findViewById(R.id.game_activity_button_4);

        mAnswerOneButton.setOnClickListener(this);
        mAnswerTwoButton.setOnClickListener(this);
        mAnswerThreeButton.setOnClickListener(this);
        mAnswerFourButton.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getCurrentQuestion();
        displayQuestion(mCurrentQuestion);

        mRemainingQuestionCount = 4;
        mScore = 0;
    }

    private void displayQuestion(final  Question question){
        mQuestionTextView.setText(question.getQuestion());
        mAnswerOneButton.setText(question.getChoiceList().get(0));
        mAnswerTwoButton.setText(question.getChoiceList().get(1));
        mAnswerThreeButton.setText(question.getChoiceList().get(2));
        mAnswerFourButton.setText(question.getChoiceList().get(3));
    }
    private QuestionBank generateQuestion(){
        Question question1 = new Question(
           "Combien de rayures y a-t-il sur le drapeau américain ?",
             Arrays.asList(
                     "9",
                     "12",
                     "13",
                     "15"
             ),
                0
        );
        Question question2 = new Question(
                "En combien de jours la Terre tourne-t-elle autour du Soleil ? ",
                Arrays.asList(
                        "12",
                        "24",
                        "50",
                        "365"
                ),
                0
        );
        Question question3 = new Question(
                "Lequel des empires suivants n’avait pas de langue écrite : l’inca, l’aztèque, l’égyptien, le romain ?",
                Arrays.asList(
                        "L'inca",
                        "L'aztèque",
                        "L'egyptien",
                        "Le romain"
                ),
                0
        );
        return new QuestionBank(Arrays.asList(question1, question2, question3));
    }

    @Override
    public void onClick(View view) {
        int index;

        if (view == mAnswerOneButton) {
            index = 0;
        }else if (view == mAnswerTwoButton) {
            index = 1;
        }else if (view == mAnswerThreeButton) {
            index = 2;
        }else if (view == mAnswerFourButton) {
            index = 3;
        }else {
            throw new IllegalStateException("Click inconnu : " + view);
        }
        //ici on incremente le score de l'utilisateur//
        if(index == mQuestionBank.getCurrentQuestion().getAnswerIndex()) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }
        
        mRemainingQuestionCount--;

        if(mRemainingQuestionCount > 0) {
            mCurrentQuestion = mQuestionBank.getNextQuestion();
            displayQuestion(mCurrentQuestion);
        } else {
            //ici on construit(builder) la boite de dialogue
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //ici on definit le titre de la boite de dialogue
            builder.setTitle("Bien joué!")
                    //ici on definit le message
                    .setMessage("Votre score est " + mScore)
                    //ici on definit le texte du bouton et on fournit l'implementation
                    //de l'interface permettanr de gerer le clic
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    })
                    //ici(create) on demande a l'instance de Builder de construire la boite de dialogue
                    .create()
                    //Ici on affiche la boite de dialogue
                    .show();
        }
    }
}