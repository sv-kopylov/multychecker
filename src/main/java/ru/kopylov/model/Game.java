package ru.kopylov.model;

import ru.kopylov.app.AllSamples;

import java.io.IOException;

public class Game {
    private AllSamples allSamples = new AllSamples();
    private Report report;
    private Sample[] samples;
    private int cnt=0;
    private int rounds =0;

    private Sample curSample;

    public void start(int numSamples){
        cnt = 0;
        rounds=numSamples;
        samples = allSamples.getSamples(numSamples);
        report = new Report();
    }

    public Sample getNext(){
        if(cnt<rounds){
            Sample smpl = samples[cnt];
            cnt++;
            return smpl;
        } else {
            return null;
        }
    }

    public boolean setNext(Result result){
        report.add(result);
        return samples[cnt-1].getAnswer()==result.getGivenAnswer();
    }

    public void report(){
        try {
            report.report();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
