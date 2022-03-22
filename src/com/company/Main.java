package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        boolean exit = false;
        String[] command = {""};
        CommandReader cr = new CommandReader();
        TextEvaluator te = new TextEvaluator();
        WeatherEvaluator we = new WeatherEvaluator();

        writeOutMan("");

        while(!exit) {



            try {
                command = cr.getCommand();
            } catch (IOException ex) {
                System.out.println(ex);
            }

            switch (command[0]){
                case "exit": exit = true; break;
                case "manual":
                    if (command.length == 2)
                        writeOutMan(command[1]);
                    else
                        writeOutMan("");
                    break;
                case "text":
                    if (command.length < 5){
                        System.out.println("Hibas parancs! Helyes hasznalat a manualban");
                        break;
                    }
                    te.evaluate(command[1], command[2], command[3], command[4]);
                    break;
                case "weather":
                    if (command.length < 3){
                        System.out.println("Hibas parancs! Helyes hasznalat a manualban");
                        break;
                    }
                    we.evaluate(command[1], command[2]);
                    break;
                default:
                    System.out.println("Hibas parancs! Helyes hasznalat a manualban");
                    break;
            }
        }

        WeatherAPI WAPI = new WeatherAPI();
        try {
            WAPI.submittingForm(0, "2000-01");
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    static void writeOutMan(String section){
        int startingBit;
        int endingbit;

        switch (section){
            case "text": startingBit = 155; endingbit = 1265; break;
            case "weather": startingBit = 1266; endingbit = 1982; break;
            default: startingBit = -1; endingbit = 1000000;
        }


        int bitCnt = 0;
        try {
            File file = new File(System.getProperty("user.dir")+"/res/manual.txt");
            FileInputStream fis = new FileInputStream(file);

            int oneByte;
            while ((oneByte = fis.read()) != -1) {
                if (bitCnt > startingBit && bitCnt < endingbit)
                    System.out.write(oneByte);

                bitCnt++;
                // System.out.print((char)oneByte); // could also do this
            }
            System.out.flush();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}

