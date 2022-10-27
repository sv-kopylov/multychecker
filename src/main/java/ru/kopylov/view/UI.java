package ru.kopylov.view;

import ru.kopylov.model.Game;
import ru.kopylov.model.Result;
import ru.kopylov.model.Sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {

    private  Game game = new Game();
    private Sample currentSample;

    private long curTime;


    JFrame mainFrame = new JFrame("~~~~~~");
    JButton startButton = new JButton("start");
    JButton respButton = new JButton("re");
    JTextArea numSamplesArea = new JTextArea("20");
    JTextArea answerArea = new JTextArea();
    JTextArea sampleTextArea = new JTextArea("");
    JTextArea infoTextArea = new JTextArea("");

    public UI() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(300, 200);
        mainFrame.getContentPane().setLayout(null);

        sampleTextArea.setEditable(false);
        infoTextArea.setEditable(false);
        respButton.setEnabled(false);
        answerArea.setEnabled(false);

        int typicalH = 30;
        int typicalGap = 5;
        int y = 5;


        // первая строка
        numSamplesArea.setBounds(typicalGap, y, 20,typicalH);
        startButton.setBounds(30, y, 70, typicalH);


        // вторая строка
        y= y+typicalH+30;
        sampleTextArea.setBounds(typicalGap, y, 100, typicalH);
        answerArea.setBounds(110, y, 30, typicalH);
        respButton.setBounds(155, y, 50, typicalH);


        // третья строка
        y= y+typicalH+typicalGap;
        infoTextArea.setBounds(typicalGap, y, 150, typicalH);

        startButton.addActionListener(startActionListener);
        respButton.addActionListener(respButtonActionListener);

        mainFrame.getContentPane().add(numSamplesArea);
        mainFrame.getContentPane().add(startButton);
        mainFrame.getContentPane().add(sampleTextArea);
        mainFrame.getContentPane().add(answerArea);
        mainFrame.getContentPane().add(infoTextArea);
        mainFrame.getContentPane().add(respButton);



        mainFrame.setVisible(true);
    }

    private final ActionListener startActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            startButton.setEnabled(false);
            numSamplesArea.setEnabled(false);
            int n = Integer.parseInt(numSamplesArea.getText());
            game.start(n);
            currentSample = game.getNext();
            sampleTextArea.setText(currentSample.getQuestion());
            curTime = System.currentTimeMillis();

            respButton.setEnabled(true);
            answerArea.setEnabled(true);
            answerArea.requestFocus();

        }
    };

    private final ActionListener respButtonActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int resp = 0;
            try {
                resp = Integer.parseInt(answerArea.getText());
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            if(resp == currentSample.getAnswer()){
                infoTextArea.setBackground(Color.WHITE);
                infoTextArea.setText("correct");
            } else {
                infoTextArea.setBackground(Color.RED);
                infoTextArea.setText("incorrect");
            }
            game.setNext(new Result(currentSample, resp, System.currentTimeMillis() - curTime));
            answerArea.setText("");

            currentSample = game.getNext();
            if(currentSample!=null){
                sampleTextArea.setText(currentSample.getQuestion());
                curTime = System.currentTimeMillis();
                answerArea.requestFocus();

            } else {

                startButton.setEnabled(true);
                numSamplesArea.setEnabled(true);
                respButton.setEnabled(false);
                answerArea.setEnabled(false);

                sampleTextArea.setText("");
                infoTextArea.setText("");
                infoTextArea.setBackground(Color.WHITE);
                game.report();

            }
        }
    };


    public static void main(String[] args) {
        new UI();
    }
}
