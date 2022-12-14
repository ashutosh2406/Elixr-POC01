import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/* Here I am storing all the user given data and results along with data and time of the operation into database*/
class DataBaseHelper {
    String driverClass = "com.mysql.cj.jdbc.Driver";
    String mySqlUrl = "jdbc:mysql://localhost:3306/elixr1";
    String userName = "root";
    String passwordOfDatabase = "Ashu@2406";
    String dateAndTimeFormat = "yyyy/MM/dd HH:mm:ss";
    String createTable = "create table audit (PathToTheFile varchar(100), SearchedWord varchar(45), DateAndTimeOfSearch varchar(45), result varchar(45), WordCount int, ErrorMessage varchar(100))";

    public void storeDataToDataBase(String searchedWord, String filepath, String resultToDatabase, int totalNoOfWords, String errorMessage) throws SQLException {
        Connection connectionToDataBase = null;
        Statement st = null;
        DateTimeFormatter dateAndTimeFormater = DateTimeFormatter.ofPattern(this.dateAndTimeFormat);
        LocalDateTime now = LocalDateTime.now();
        String currentDateAndTime = dateAndTimeFormater.format(now);
        try {
            DataBaseHelper object = new DataBaseHelper();
            connectionToDataBase = object.connectionToDatabase();
            st = connectionToDataBase.createStatement();
            DatabaseMetaData checkIfTableIsThere = connectionToDataBase.getMetaData();
            ResultSet tables = checkIfTableIsThere.getTables(null, null, "audit", null);
            if (tables.next()) {
                st.execute("INSERT INTO audit VALUES ('" + filepath + "','" + searchedWord + "','" + currentDateAndTime + "','" + resultToDatabase + "'," + totalNoOfWords + ",'" + errorMessage + "')");
            } else {
                this.createTable(filepath, searchedWord, currentDateAndTime, resultToDatabase, totalNoOfWords, errorMessage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Objects.requireNonNull(connectionToDataBase).close();
        }
    }

    private void createTable(String filepath, String searchedWord, String currentDateAndTime, String resultToDatabase, int totalNoOfWords, String errorMessage) throws SQLException {
        Connection connectionToDatabase = connectionToDatabase();
        try {
            Statement st = connectionToDatabase.createStatement();
            st.execute(this.createTable);
            st.execute("INSERT INTO audit VALUES ('" + filepath + "','" + searchedWord + "','" + currentDateAndTime + "','" + resultToDatabase + "'," + totalNoOfWords + ",'" + errorMessage + "')");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Objects.requireNonNull(connectionToDatabase).close();
        }
    }

    private Connection connectionToDatabase() throws SQLException {
        Connection connectionToDataBase = null;
        try {
            Class.forName(this.driverClass);
            connectionToDataBase = DriverManager.getConnection(this.mySqlUrl, this.userName, this.passwordOfDatabase);
            return connectionToDataBase;
        } catch (Exception e) {
            e.printStackTrace();
            return connectionToDataBase;
        } finally {
            Objects.requireNonNull(connectionToDataBase).close();
        }
    }
}

