import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


class FileReader {

    /* Checking if the file is either txt or json  */

    public static boolean isFileExists(String filepath) {
        if (filepath.endsWith("txt") || filepath.endsWith("json")) {
            readingFileContent(filepath);
            return true;
        } else {
            return false;
        }
    }

    public static void readingFileContent(String filePath) {
        try {
            Path filepath = Path.of(filePath);
            FileSearchApplication.contentOfFile = Files.readAllLines(filepath).get(0);
            FileSearchApplication.contentOfFile = FileSearchApplication.contentOfFile.replaceAll("[^a-zA-Z0-9]", " ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

