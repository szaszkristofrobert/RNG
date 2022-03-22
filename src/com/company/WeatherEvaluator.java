package com.company;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class WeatherEvaluator {
    FileManager fm = new FileManager();

    void evaluate(String inputname, String outputname){
        try {
            BufferedReader br = fm.getBr(inputname);
            FileWriter myWriter = fm.getWriter(outputname);

            float[] sor = new float[12];

            float[] paratlansor = {0,0,0, 0,0,0, 0,0,0, 0,0,0};

            String[] tempSor;

            String line;

            while ((line = br.readLine())!= null) {
                tempSor = line.split("\t");

                for (int i = 0; i < 12; i++) {
                    if (tempSor[i+1].equals("M"))
                        sor[i] = -1;
                    else
                        sor[i] = Float.parseFloat(tempSor[i + 1]);
                    if(paratlansor[11] == 0){
                        paratlansor[i] = sor[i];
                    }
                    else{
                        if (paratlansor[i] == -1 || sor[i] == -1);
                        else if(paratlansor[i] < sor[i])
                            myWriter.write("1");
                        else if(paratlansor[i] > sor[i])
                            myWriter.write("0");

                        paratlansor[i] = 0;
                    }
                }

            }


            myWriter.close();
            System.out.flush();
            System.out.println("Weather data evaluated!");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
