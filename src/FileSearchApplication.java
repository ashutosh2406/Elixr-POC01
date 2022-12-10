/*It is the main class where I am taking the filepath as an input from user and checking the presence of seached word*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

public class FileSearchApplication {
    protected static String contentOfFile, filepath, searchedWord;


    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("enter file path");
            filepath = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Processing....");
        SpawnThread ch = new SpawnThread();
        ch.run();
    }

    /* checking the presence of the searched word and counting its repetation in file and also counting the total no of words in file */
    public static void isWordExists(String searchedWord) throws SQLException {
        ArrayList allWordsOfFile = new ArrayList();
        int repetationOfWord = 0;
        String temp = "";
        for (int i = 0; i < contentOfFile.length(); i++) {
            char eachCharacterOfFile = contentOfFile.charAt(i);
            temp = temp + eachCharacterOfFile;
            if (eachCharacterOfFile == ' ') {
                allWordsOfFile.add(temp);
                temp = "";
            }
        }
        if (contentOfFile.contains(searchedWord)) {
            System.out.println("got the word is  present");
            DataBaseHelper db = new DataBaseHelper(FileSearchApplication.searchedWord, filepath, "Success", allWordsOfFile.size(), "");
        } else {
            String errorMessage = "Serched word is not present";
            System.out.println(errorMessage);
            DataBaseHelper db = new DataBaseHelper(FileSearchApplication.searchedWord, filepath, "Error", allWordsOfFile.size(), "");
        }
    }

    /* Sending some error message to database if the file path given by the user is invalid */
    public static void errorToDataBase(String searchedWord) throws SQLException {
        String errorMessage = "invalid File Path";
        System.out.println(errorMessage);
        DataBaseHelper db = new DataBaseHelper(FileSearchApplication.searchedWord, filepath, "invalid File Path", 0, errorMessage);
    }
}
