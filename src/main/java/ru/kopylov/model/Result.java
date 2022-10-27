package ru.kopylov.model;

public class Result {
    private Sample sample;
    private int givenAnswer;
    private long takenTime;

    public Result(Sample sample, int givenAnswer, long takenTime) {
        this.sample = sample;
        this.givenAnswer = givenAnswer;
        this.takenTime = takenTime;
    }

    public int getGivenAnswer() {
        return givenAnswer;
    }

    @Override
    public String toString() {
        return isCorrect()+"  "+sample.getQuestion()+ String.format(" corr: %d, acc: %d, time: %d", sample.getAnswer(), givenAnswer, takenTime);
    }

    public boolean isCorrect(){
        return sample.getAnswer()== givenAnswer;
    }
}
