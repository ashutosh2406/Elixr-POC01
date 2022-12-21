import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;


class FileReader implements Runnable {
    protected static int repetitionOfSearchedWord = 1;
    protected String contentOfFile = null;
    protected String filepath = null;
    protected String searchedWord;

    /* Checking if the file is either txt or json  */
   synchronized public void run() {
       this.filepath = FileSearchApplication.filepath;
        this.searchedWord = FileSearchApplication.searchedWord;
        if (this.filepath.endsWith("txt") || this.filepath.endsWith("json")) {
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


  synchronized   public void readFileContent(File filepath) {

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

  synchronized   public void isWordExists() throws SQLException {
        StringTokenizer t = new StringTokenizer(contentOfFile);
        String word = "";
        while (t.hasMoreTokens()) {
            word = t.nextToken();
            if (word.equals(searchedWord)) {
                repetitionOfSearchedWord++;
            }
        }

     Thread anotherThread=new Thread(new FileSearchApplication());
        anotherThread.start();
    }

}
