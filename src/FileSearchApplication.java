/*It is the main class where I am taking the filepath as an input from user and checking the presence of seached word*/

import java.sql.SQLException;

public class FileSearchApplication implements Runnable {
    public static FileSearchApplication o1;
    protected static String filepath = null;
    protected static String searchedWord = null;

    synchronized public static void main(String[] args) {

        if (args.length == 2) {
            filepath = args[0];
            searchedWord = args[1];
        }
        System.out.println("Processing....");
        Thread startChildThread = new Thread(new FileReader());
        startChildThread.start();
    }

    public void run() {
        DataBaseHelper databaseObject = new DataBaseHelper();
        if (FileReader.repetitionOfSearchedWord != 1) {
            System.out.println("got the word, It is  present " + FileReader.repetitionOfSearchedWord + " times inside the file");
            try {
                databaseObject.storeDataToDataBase(searchedWord, filepath, "Success", FileReader.repetitionOfSearchedWord, "");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            String errorMessage = "Searched word is not present";
            System.out.println(errorMessage);
            try {
                databaseObject.storeDataToDataBase(searchedWord, filepath, "Error", 0, "");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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
