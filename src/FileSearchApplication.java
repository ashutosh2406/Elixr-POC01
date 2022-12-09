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
            System.out.println("got the word");
            DataBaseHelper db = new DataBaseHelper(word, filepath, "Success", totalNoOfWordsInFile, "");
        } else {
            String errorMessage = "Serched word is not present";
            System.out.println(errorMessage);
            DataBaseHelper db = new DataBaseHelper(word, filepath, "Error", totalNoOfWordsInFile, "");
        }
    }

    /* Sending some error message to database if the file path given by the user is invalid */
    protected static void errorToDataBase(String searchedWord) throws SQLException {
        String errorMessage = "invalid File Path";
        System.out.println(errorMessage);
        DataBaseHelper db = new DataBaseHelper(word, filepath, "invalid File Path", totalNoOfWordsInFile, errorMessage);
    }
}
