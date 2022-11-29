package com.example.supermegagigaquizz.model.model;

import java.util.List;
import java.io.Serializable;
import java.util.Collections;

public class QuestionBank implements Serializable {
    private final List<Question> mQuestionList;
    private int mQuestionIndex;

    public QuestionBank(List<Question> questionList) {
        mQuestionList = questionList;

        Collections.shuffle(mQuestionList);
    }

    public Question getCurrentQuestion() {
        return mQuestionList.get(mQuestionIndex);
    }

    public Question getNextQuestion() {
        mQuestionIndex++;
        return getCurrentQuestion();
    }
}
