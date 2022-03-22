package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class CommandReader {

    String[] getCommand()throws IOException {

        BufferedReader bi = new BufferedReader(
                new InputStreamReader(System.in));

        String[] command;

        // Display message
        //System.out.println("enter command");

        // Reading input a string
        command = bi.readLine().split(" ");

        //System.out.println(command[0]);

        return command;


    }

}