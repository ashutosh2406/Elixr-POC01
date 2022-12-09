/*It is the main class where I am taking the filepath as an input from user and checking the presence of seached word*/

import java.sql.SQLException;
import java.util.Scanner;

public class FileSearchApplication {
    protected static String contentOfFile, filepath, word;
    protected static int totalNoOfWordsInFile;

    public static void main(String[] args) {
        Scanner scannerCommandLineInputForPath = new Scanner(System.in);
        System.out.println("enter file path");
        filepath = scannerCommandLineInputForPath.next();
        System.out.println("Processing....");
        SpawnThread ch = new SpawnThread();
        ch.run();
    }

    /* checking the presence of the searched word and counting its repetation in file and also counting the total no of words in file */
    protected static void wordSearching(String searchedWord) throws SQLException {
        if (contentOfFile.contains(searchedWord)) {
            String resultToDatabase = "Success";
            System.out.println("got the word");
            DataBaseHelper db = new DataBaseHelper(word, filepath, resultToDatabase, totalNoOfWordsInFile, "");
        } else {
            String resultToDatabase = "Error";
            String errorMessage = "Serched word is not present";
            System.out.println(resultToDatabase);
            DataBaseHelper db = new DataBaseHelper(word, filepath, resultToDatabase, totalNoOfWordsInFile, "");
        }
    }

    /* Sending some error message to database if the file path given by the user is invalid */
    protected static void errorToDataBase(String searchedWord) throws SQLException {
        String resultToDatabase = "invalid File Path";
        String errorMessage = resultToDatabase;
        System.out.println(resultToDatabase);
        DataBaseHelper db = new DataBaseHelper(word, filepath, resultToDatabase, totalNoOfWordsInFile, errorMessage);
    }
}
