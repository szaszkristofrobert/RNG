package com.company;

import com.gargoylesoftware.htmlunit.javascript.host.Console;
import org.eclipse.jetty.util.IO;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class WeatherEvaluator {
    FileManager fm = new FileManager();

    void evaluate(String inputname, String outputname, boolean averageTrue, int oszlop){
        try {
            BufferedReader br = fm.getWeatherReader(inputname);
            FileWriter myWriter = fm.getWriter(outputname);

            LinkedList<java.lang.Float> dayValues = resetDayValues();

            String line;
            String[] splitLine;

            //int nap=0;
            while ((line = br.readLine())!= null) {

                splitLine= line.split("\t");

                try {
                    if(splitLine.length <= oszlop){
                        //System.out.println("Empty line");
                    }
                    else if ((splitLine[1].equals("Average") && averageTrue) || (splitLine[1].equals("Normal") && !averageTrue)) {
                        float elvalaszto = Float.parseFloat(splitLine[oszlop]);
                        writeBits(elvalaszto, dayValues, myWriter);
                        dayValues = resetDayValues();
                    }
                    else if(!splitLine[1].equals("Average") && !splitLine[1].equals("Normal") && !splitLine[1].equals("Sum") && !splitLine[1].equals("")){
                        dayValues.add(Float.parseFloat(splitLine[oszlop]));
                    }
                }
                catch(NumberFormatException ex) {
                    //System.out.println("NaN: " + splitLine[oszlop]);
                }
            }


            myWriter.close();
            System.out.flush();
            System.out.println("Weather data evaluated!");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    void writeBits(float elvalaszto, LinkedList<java.lang.Float> dayValues, FileWriter myWriter) throws IOException {
        float value;
        while(dayValues.size() > 0){
            value = dayValues.pop();
            if (elvalaszto > value)
                myWriter.write("0");
            if (elvalaszto < value)
                myWriter.write("1");
        }
    }

    LinkedList<java.lang.Float> resetDayValues (){
        return new LinkedList<java.lang.Float>();
        /*float[] dayValues = new float[40];
        for (int i = 0; i < 31; i++){
            dayValues[i] = -1;
        }
        return dayValues;*/
    }
}
