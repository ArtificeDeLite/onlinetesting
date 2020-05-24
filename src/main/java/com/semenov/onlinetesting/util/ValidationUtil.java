package com.semenov.onlinetesting.util;

import com.semenov.onlinetesting.exception.IllegalRequestDataException;

import static com.semenov.onlinetesting.controller.QuestionController.NUMBER_OF_QUESTIONS;

public class ValidationUtil {

    public static void numberCheck(int number) {
        if (number <= 0 || number > NUMBER_OF_QUESTIONS) {
            throw new IllegalRequestDataException("Wrong number of question");
        }
    }

    public static void notBlankCheck(String checked, String name) {
        if (checked == null || checked.isEmpty()) {
            throw new IllegalRequestDataException(name + " must not be blank");
        }
    }
}
