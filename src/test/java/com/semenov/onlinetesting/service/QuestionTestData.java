package com.semenov.onlinetesting.service;

import com.semenov.onlinetesting.model.Question;

public class QuestionTestData {

    private static final int START_SEQ = 103;

    public static final int QUESTION_1_ID = START_SEQ;
    public static final int QUESTION_2_ID = START_SEQ + 1;
    public static final int QUESTION_3_ID = START_SEQ + 2;
    public static final int QUESTION_4_ID = START_SEQ + 3;
    public static final int QUESTION_5_ID = START_SEQ + 4;

    public static final Question QUESTION_1 = new Question(QUESTION_1_ID, "Принцип в программировании\\n1.SOLID\\n2.COVID\\n3.DAMIT\\n4.BANDIT", "1");
    public static final Question QUESTION_2 = new Question(QUESTION_2_ID, "Какой элемент переодической системы химических элементов обозначается как Ag?", "Серебро");
    public static final Question QUESTION_3 = new Question(QUESTION_3_ID, "Единица измерения силы тока\\n1.Ампер\\n2.Вольт\\n3.Ватт\\n4.Ом", "1");
    public static final Question QUESTION_4 = new Question(QUESTION_4_ID, "Самое глубокое озеро на планете?", "Байкал");
    public static final Question QUESTION_5 = new Question(QUESTION_5_ID, "Какой город изображен на современной российской купюре 1000р?", "Ярославль");


}
