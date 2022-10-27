package ru.kopylov.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Report {
    private ArrayList<Result> list = new ArrayList<Result>();
    public void add(Result result){
        list.add(result);
    }

    public void report() throws IOException {
        String fileName = "rep_"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            bw.write(correctness()+"\n");
            for(Result res: list){
                bw.write(res.toString()+"\n");

            }
        }
    }

    private String correctness(){
        long total = list.size();
        long correct = list.stream().filter(r -> r.isCorrect()).count();
        long incorrect = total - correct;
        return String.format("total: %d, correct: %d, incorrect: %d", total, correct, incorrect);
    }
}
