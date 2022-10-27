package ru.kopylov.model;

public class Sample {
    private String question;
    private int answer;

    public Sample(String question, int answer) {
        this.question = question;
        this.answer = answer;
    }

    public int getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }
}
