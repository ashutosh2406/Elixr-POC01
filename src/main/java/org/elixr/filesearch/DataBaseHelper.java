package org.elixr.filesearch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/* Here I am storing all the user given data and results along with data and time of the operation into database*/
class DataBaseHelper {
   protected DataBaseHelper(String word, String filepath, int totalNoOfWords, String errorMessage) throws SQLException {
        storingDataToDataBase(word, filepath, totalNoOfWords, errorMessage);
    }

    private static void storingDataToDataBase(String word, String filepath, int totalNoOfWords, String errorMessage) throws SQLException {
        Connection connectionToDataBase = null;
        try {
            DateTimeFormatter dateAndTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String currentDateAndTime = dateAndTimeFormat.format(now);         //current date and time.
            String result = "successful";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connectionToDataBase = DriverManager.getConnection("jdbc:mysql://localhost:3306/elixr1", "root", "Ashu@2406");
            Statement st = connectionToDataBase.createStatement();
            //st.execute("create table audit(PathToTheFile varchar(100),SearchedWord varchar(45),DateAndTimeOfSearch varchar(45),result varchar(45),WordCount int,ErrorMessage varchar(100))");
            st.execute("INSERT INTO audit VALUES ('" + filepath + "','" + word + "','" + currentDateAndTime + "','" + result + "'," + totalNoOfWords + ",'" + errorMessage + "')");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            connectionToDataBase.close();
        }
    }
}
