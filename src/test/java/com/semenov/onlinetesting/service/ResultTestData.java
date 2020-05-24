package com.semenov.onlinetesting.service;

import com.semenov.onlinetesting.model.Result;

public class ResultTestData {

    private static final int START_SEQ = 108;

    public static final int RESULT_1_ID = START_SEQ;
    public static final int RESULT_2_ID = START_SEQ + 1;
    public static final int RESULT_3_ID = START_SEQ + 2;
    public static final int RESULT_4_ID = START_SEQ + 3;
    public static final int RESULT_5_ID = START_SEQ + 4;

    public static final Result RESULT_1 = new Result(RESULT_1_ID, 100, 103, "1", true);
    public static final Result RESULT_2 = new Result(RESULT_2_ID, 100, 104, "Серебро", true);
    public static final Result RESULT_3 = new Result(RESULT_3_ID, 100, 105, "1", true);
    public static final Result RESULT_4 = new Result(RESULT_4_ID, 100, 106, "Байкал", true);
    public static final Result RESULT_5 = new Result(RESULT_5_ID, 100, 107, "Москва", false);

}
