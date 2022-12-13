/*It is the main class where I am taking the filepath as an input from user and checking the presence of seached word*/

import java.sql.SQLException;
import java.util.StringTokenizer;

public class FileSearchApplication {

    public static void main(String[] args) {
        String filepath = null;
        String searchedWord = null;
        if (args.length == 2) {
            filepath = args[0];
            searchedWord = args[1];
        }
        System.out.println("Processing....");
        FileReader childThread = new FileReader(filepath);
        childThread.start();
        FileSearchApplication object = new FileSearchApplication();
        try {
            if (childThread.contentOfFile != null) {
                System.out.println("got the file");
                object.isWordExists(filepath, searchedWord, childThread.contentOfFile);
            } else {
                DataBaseHelper databasehelperObj = new DataBaseHelper();
                databasehelperObj.storeDataToDataBase(searchedWord, filepath, "Error", 0, "The file is empty");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /* checking the presence of the searched word and counting its repetation in file  */
    public void isWordExists(String filepath, String searchedWord, String contentOfFile) throws SQLException {
        int repetationOfSearchedWord = 1;
        StringTokenizer t = new StringTokenizer(contentOfFile);
        String word = "";
        while (t.hasMoreTokens()) {
            word = t.nextToken();
            if (word.equals(searchedWord)) {
                repetationOfSearchedWord++;
            }
        }
        DataBaseHelper object = new DataBaseHelper();
        if (repetationOfSearchedWord != 1) {
            System.out.println("got the word, It is  present " + repetationOfSearchedWord + " times inside the file");
            object.storeDataToDataBase(searchedWord, filepath, "Success", repetationOfSearchedWord, "");
        } else {
            String errorMessage = "Serched word is not present";
            System.out.println(errorMessage);
            object.storeDataToDataBase(searchedWord, filepath, "Error", 0, "");
        }
    }

    /* Sending some error message to database if the file path given by the user is invalid */
    public void errorToDataBase(String filepath, String searchedWord, String contentOfFile) throws SQLException {
        String errorMessage = "invalid File Path";
        System.out.println(errorMessage);
        DataBaseHelper object = new DataBaseHelper();
        object.storeDataToDataBase(searchedWord, filepath, "invalid File Path", 0, errorMessage);
    }
}
