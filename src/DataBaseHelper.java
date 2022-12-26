import java.sql.*;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/* Here I am storing all the user given data and results along with data and time of the operation into database*/
class DataBaseHelper {

    public void storeDataToDataBase(String searchedWord, String filepath, String resultToDatabase, int totalOccuranceOfWord, String errorMessage) throws SQLException {
        Connection connectionToDataBase = null;
        Statement st = null;
        DateTimeFormatter dateAndTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_AND_TIME_FORMAT);
        LocalDateTime now = LocalDateTime.now();
        String currentDateAndTime = dateAndTimeFormatter.format(now);
        try {
            connectionToDataBase = this.connectionToDataBase();
            st = connectionToDataBase.createStatement();
            DatabaseMetaData checkIfTableIsThere = connectionToDataBase.getMetaData();
            ResultSet tables = checkIfTableIsThere.getTables(null, null, "audit", null);
            if (tables.next()) {
                String query = MessageFormat.format("INSERT INTO " + Constants.TABLE_NAME + " VALUES({0},{1},{2},{3},{4},{5})", "'" + filepath + "'", "'" + searchedWord + "'", "'" + currentDateAndTime + "'", "'" + resultToDatabase + "'", "'" + totalOccuranceOfWord + "'", "'" + errorMessage + "'");
                st.execute(query);
            } else {
                this.createTable(filepath, searchedWord, currentDateAndTime, resultToDatabase, totalOccuranceOfWord, errorMessage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Objects.requireNonNull(connectionToDataBase).close();
        }
    }

    private void createTable(String filepath, String searchedWord, String currentDateAndTime, String resultToDatabase, int totalOccuranceOfWord, String errorMessage) throws SQLException {
        Connection connectionToDatabase = connectionToDataBase();
        try {
            Statement st = connectionToDatabase.createStatement();
            st.execute(Constants.QUERY_TO_CREATE_TABLE);
            String query = MessageFormat.format("INSERT INTO " + Constants.TABLE_NAME + " VALUES({0},{1},{2},{3},{4},{5})", "'" + filepath + "'", "'" + searchedWord + "'", "'" + currentDateAndTime + "'", "'" + resultToDatabase + "'", "'" + totalOccuranceOfWord + "'", "'" + errorMessage + "'");
            st.execute(query);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Objects.requireNonNull(connectionToDatabase).close();
        }
    }

    private Connection connectionToDataBase() throws SQLException {
        Connection connectionToDataBase = null;
        try {
            Class.forName(Constants.DRIVER_CLASS);
            connectionToDataBase = DriverManager.getConnection(Constants.MYSQL_URL, Constants.USERNAME, Constants.PASSWORD_OF_DATABASE);
            return connectionToDataBase;
        } catch (Exception e) {
            e.printStackTrace();
            return connectionToDataBase;
        }
    }
}

