import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/* Here I am storing all the user given data and results along with data and time of the operation into database*/
class DataBaseHelper {

    protected DataBaseHelper(String word, String filepath, String resultToDatabase, int totalNoOfWords, String errorMessage) throws SQLException {
        storingDataToDataBase(word, filepath, resultToDatabase, totalNoOfWords, errorMessage);
    }

    private static void storingDataToDataBase(String word, String filepath, String resultToDatabase, int totalNoOfWords, String errorMessage) throws SQLException {
        Connection connectionToDataBase = null;
        String driveClass = "com.mysql.cj.jdbc.Driver";
        String mySqlUrl = "jdbc:mysql://localhost:3306/elixr1";
        String userName = "root";
        String passwordOfDatabase = "Ashu@2406";
        String dateAndTimeFormat = "yyyy/MM/dd HH:mm:ss";

        try {
            DateTimeFormatter dateAndTimeFormater = DateTimeFormatter.ofPattern(dateAndTimeFormat);
            LocalDateTime now = LocalDateTime.now();
            String currentDateAndTime = dateAndTimeFormater.format(now);         //current date and time.
            Class.forName(driveClass);
            connectionToDataBase = DriverManager.getConnection(mySqlUrl, userName, passwordOfDatabase);
            Statement st = connectionToDataBase.createStatement();
            st.execute("create table audit(PathToTheFile varchar(100),SearchedWord varchar(45),DateAndTimeOfSearch varchar(45),result varchar(45),WordCount int,ErrorMessage varchar(100))");
            st.execute("INSERT INTO audit VALUES ('" + filepath + "','" + word + "','" + currentDateAndTime + "','" + resultToDatabase + "'," + totalNoOfWords + ",'" + errorMessage + "')");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Objects.requireNonNull(connectionToDataBase).close();
        }
    }
}
