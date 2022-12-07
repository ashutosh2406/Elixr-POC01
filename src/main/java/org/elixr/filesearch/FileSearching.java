package org.elixr.filesearch;

import java.io.*;
import java.util.Scanner;
/* Checking the file avaibility and storing it in a memory after reading its content */
class FileSearching {
  /* Filtering the txt and json file*/
   protected FileSearching(String file)  {
        if(file.endsWith("txt") || file.endsWith("json")){
        FileChecking(file);
        }
    }

    private static void FileChecking(String filepath){
        try {
            String sn="";
            File f = new File(filepath);
            Scanner s= new Scanner(f);
            while( s.hasNextLine()){
                sn=sn+s.nextLine();
            FileSearchApplication.contentOfFile = sn;
        }
            FileSearchApplication.contentOfFile = FileSearchApplication.contentOfFile.replaceAll("[^a-zA-Z]"," ");

        }
        catch(Exception e){
            System.out.println(e);
        }
    }}

