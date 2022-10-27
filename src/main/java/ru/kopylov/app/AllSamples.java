package ru.kopylov.app;

import ru.kopylov.model.Sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllSamples {
    private List<Sample> list = new ArrayList<Sample>();

    public AllSamples() {
        for(int i=2;i<10;i++){
            for (int j = 2; j<10; j++){
                list.add(new Sample(String.format("%d x %d = ", i,j), i*j));
            }
        }

    }

    public Sample[] getSamples(int num){
        if(num>list.size()){
            num = list.size();
        }
        Collections.shuffle(list);
        Sample[] arr = new Sample[num];
        for(int i=0; i<num; i++){
            arr[i] = list.get(i);
        }
        return arr;
    }
}
