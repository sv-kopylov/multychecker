package ru.kopylov;

import java.util.Random;

public class App
{
    public static void main( String[] args )
    {
        Random rnd = new Random(System.currentTimeMillis());
        for(int i= 0; i<10; i++){
            double d1 = rnd.nextDouble();
            double d2 = rnd.nextDouble();

            int i1 = (int)(d1*1000);
            int i2 = (int)(d2*10000);

            System.out.println(String.format("%d : %d = ", i1*i2, i1));

        }

    }
}
