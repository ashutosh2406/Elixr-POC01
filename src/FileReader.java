import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;


class FileReader extends Thread {
    String contentOfFile = null;
    String filepath = null;

    protected FileReader(String filepath) {
        this.filepath = filepath;
    }

    /* Checking if the file is either txt or json  */
    public void start() {
        try {
            if (this.filepath.endsWith("txt") || this.filepath.endsWith("json")) {
                readFileContent(filepath);
            }
        } catch (NullPointerException e) {
            System.out.println("Check the path Properly");
            FileSearchApplication object = new FileSearchApplication();
            try {
                object.errorToDataBase(this.filepath, "", "");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void readFileContent(String filepath) {

        try {
            List<String> allLines = Files.readAllLines(Paths.get(filepath));
            for (String temporaryMemoryToFileContent : allLines) {
                contentOfFile = temporaryMemoryToFileContent.replaceAll("^a-zA-Z", " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
