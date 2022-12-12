/*It is the main class where I am taking the filepath as an input from user and checking the presence of seached word*/

import java.sql.SQLException;
import java.util.StringTokenizer;

public class FileSearchApplication {
    protected static String contentOfFile, filepath, searchedWord;


    public static void main(String[] args) {
        if (args.length == 2) {
            filepath = args[0];
            searchedWord = args[1];
        }
        System.out.println("Processing....");
        SpawnThread childThread = new SpawnThread();
        childThread.start();
    }

    /* checking the presence of the searched word and counting its repetation in file and also counting the total no of words in file */
    public static void isWordExists() throws SQLException {
        int repetationOfSearchedWord = 1;
        int totalNoOfWordsInFile = 0;
        StringTokenizer t = new StringTokenizer(contentOfFile);
        String word = "";
        while (t.hasMoreTokens()) {
            word = t.nextToken();
            totalNoOfWordsInFile++;
            if (word.equals(searchedWord)) {
                repetationOfSearchedWord++;
            }
        }
        DataBaseHelper object = new DataBaseHelper();
        if (repetationOfSearchedWord != 1) {
            System.out.println("got the word, It is  present " + repetationOfSearchedWord + " times inside the file");
            object.storingDataToDataBase(FileSearchApplication.searchedWord, filepath, "Success", totalNoOfWordsInFile, "");
        } else {
            String errorMessage = "Serched word is not present";
            System.out.println(errorMessage);
            object.storingDataToDataBase(FileSearchApplication.searchedWord, filepath, "Error", 0, "");
        }
    }

    /* Sending some error message to database if the file path given by the user is invalid */
    public static void errorToDataBase() throws SQLException {
        String errorMessage = "invalid File Path";
        System.out.println(errorMessage);
        DataBaseHelper object = new DataBaseHelper();
        object.storingDataToDataBase(FileSearchApplication.searchedWord, filepath, "invalid File Path", 0, errorMessage);
    }
}
