import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;


class FileReader implements Callable<Integer> {
    private final String searchedWord;
    private final String filepath;
    private int repetitionOfSearchedWord = 0;
    private String contentOfFile;

    FileReader(String filepath, String searchedWord) {
        this.filepath = filepath;
        this.searchedWord = searchedWord;
    }

    @Override
    public Integer call() throws Exception {
        validateFilePath();

        return repetitionOfSearchedWord;
    }


    /* Checking if the file is either txt or json  */
    public void validateFilePath() {
        if (this.filepath.endsWith(Constants.TXT_EXTENSION) || this.filepath.endsWith(Constants.JSON_EXTENSION)) {
            File checkFileAvailability = new File(this.filepath);
            if (checkFileAvailability.exists()) {
                readFileContent(checkFileAvailability);
            }
        } else {
            FileSearchApplication object = new FileSearchApplication();
            try {
                object.errorToDataBase(this.filepath, "", "");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


    public void readFileContent(File filepath) {

        try {
            List<String> allLines = Files.readAllLines(filepath.toPath());
            for (String temporaryMemoryToFileContent : allLines) {
                contentOfFile = temporaryMemoryToFileContent.replaceAll("^a-zA-Z", " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (contentOfFile != null) {
                System.out.println("got the file");
                isWordExists();
            } else {
                DataBaseHelper databasehelperObj = new DataBaseHelper();
                databasehelperObj.storeDataToDataBase(searchedWord, this.filepath, "Error", 0, "The file is empty");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void isWordExists() throws SQLException {
        StringTokenizer t = new StringTokenizer(contentOfFile);
        String word = "";
        while (t.hasMoreTokens()) {
            word = t.nextToken();
            if (word.equals(searchedWord)) {
                repetitionOfSearchedWord++;
            }
        }
    }
}


