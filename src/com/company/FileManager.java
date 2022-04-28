package com.company;

import java.io.*;


public class FileManager {

    public FileInputStream getFis(String inputname) throws IOException{
        File file = new File(System.getProperty("user.dir") + "/inputs/" + inputname + ".txt");
        return new FileInputStream(file);
    }

    public BufferedReader getBr(String inputname)throws IOException{
        File file = new File(System.getProperty("user.dir") + "/inputs/" + inputname + ".txt");
        return new BufferedReader(new FileReader(file));
    }

    public BufferedReader getWeatherReader(String inputname)throws IOException{
        File file = new File(System.getProperty("user.dir") + "/res/" + inputname + ".txt");
        return new BufferedReader(new FileReader(file));
    }

    public FileWriter getWriter(String outputname) throws IOException{
        //output file letrehozasa
        File myObj = new File(System.getProperty("user.dir") + "/outputs/" + outputname + ".txt");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
            System.out.println("File already exists.");
            //return;
        }
        return new FileWriter(myObj, true);
    }

    public FileWriter getStatWriter()throws IOException{
        //output file letrehozasa
        File myObj = new File(System.getProperty("user.dir") + "/res/magyarBetuStat.txt");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
            System.out.println("File already exists.");
            //return;
        }
        return new FileWriter(myObj, true);
    }

    public FileWriter getWeatherWriter(String outputname)throws IOException{
        //output file letrehozasa
        File myObj = new File(System.getProperty("user.dir") + "/res/" + outputname +".txt");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
            System.out.println("File already exists.");
            //return;
        }
        return new FileWriter(myObj, true);
    }
}
