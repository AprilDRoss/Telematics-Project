package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadHTML {

    public static void main(String[] args) {
        String[] fileContents = getFileContents("dashboard.html");

        for (String line : fileContents) {
            System.out.println(line);
        }
    }

    //Returns the contents of the given file as a String[] separated by lines
    //If it can't find the file it will return null after printing an error
    public static String [] getFileContents (String fileName) {
        File file = new File (fileName);
        try {
            Scanner fileScanner = new Scanner(file);
            List<String> fileContents = new ArrayList<>();
            while (fileScanner.hasNext()) {
                fileContents.add(fileScanner.nextLine());
            }
            return fileContents.toArray(new String[0]); //Converts the list to an array
        } //Since this time we are asking for data back from the file
        //We have to specify what to do if it isn't found
        catch (FileNotFoundException ex) {
            System.out.println("Could not find file *" + fileName + "*");
            ex.printStackTrace();
            return null;
        }
    }
}








