package com.company;

import org.eclipse.jetty.util.IO;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class WeatherEvaluator {
    FileManager fm = new FileManager();

    void evaluate(String inputname, String outputname, boolean averageTrue, int oszlop){
        try {
            BufferedReader br = fm.getWeatherReader(inputname);
            FileWriter myWriter = fm.getWriter(outputname);

            float[] sor = new float[12];

            float[] paratlansor = {0,0,0, 0,0,0, 0,0,0, 0,0,0};

            float[] dayValues = resetDayValues();
            int[] results = new int[40];

            String[] tempSor;

            String line;
            int i=0;
            while ((line = br.readLine())!= null) {

                tempSor = line.split("\t");

                try {
                    if (i > 40){
                        dayValues = resetDayValues();
                        i = 0;
                    }
                    if (tempSor.length == 0){
                        i++;
                    }
                    else if ((tempSor[1].equals("Average") && averageTrue) || (tempSor[1].equals("Normal") && !averageTrue)) {
                        float elvalaszto = Float.parseFloat(tempSor[oszlop]);
                        writeBits(elvalaszto, dayValues, myWriter);
                        dayValues = resetDayValues();
                        i = 0;
                    }
                    else if(!tempSor[1].equals("Average") && !tempSor[1].equals("Normal") && !tempSor[1].equals("Sum")){
                        i++;
                        dayValues[i-1] = Float.parseFloat(tempSor[oszlop]);

                    }
                }
                catch(NumberFormatException ex) {
                    System.out.println("NaN: " + tempSor[oszlop]);

                }



                /*for (int i = 0; i < 12; i++) {
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
                }*/

            }


            myWriter.close();
            System.out.flush();
            System.out.println("Weather data evaluated!");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    void writeBits(float elvalaszto, float[] dayValues, FileWriter myWriter) throws IOException {
        int[] results = new int[31];
        for (int i = 0; i < 31; i++){
            if (dayValues[i] == -1);
                //results[i] = -1;
            else if (dayValues[i] < elvalaszto)
                myWriter.write("0");
            else if (dayValues[i] > elvalaszto)
                myWriter.write("1");
            else;
                //results[i] = -1;
        }
        //return results;
    }

    float[] resetDayValues (){
        float[] dayValues = new float[40];
        for (int i = 0; i < 31; i++){
            dayValues[i] = -1;
        }
        return dayValues;
    }
}
