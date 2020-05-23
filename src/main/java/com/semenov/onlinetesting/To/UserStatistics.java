package com.semenov.onlinetesting.To;

public class UserStatistics {

    private int passingPercent;
    private int abovePercent;
    private int belowPercent;

    public UserStatistics() {
    }

    public UserStatistics(int passingPercent, int abovePercent, int belowPercent) {
        this.passingPercent = passingPercent;
        this.abovePercent = abovePercent;
        this.belowPercent = belowPercent;
    }

    public int getPassingPercent() {
        return passingPercent;
    }

    public void setPassingPercent(int passingPercent) {
        this.passingPercent = passingPercent;
    }

    public int getAbovePercent() {
        return abovePercent;
    }

    public void setAbovePercent(int abovePercent) {
        this.abovePercent = abovePercent;
    }

    public int getBelowPercent() {
        return belowPercent;
    }

    public void setBelowPercent(int belowPercent) {
        this.belowPercent = belowPercent;
    }
}
