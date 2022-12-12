import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


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
            List<String> allLines = Files.readAllLines(Paths.get(FileSearchApplication.filepath));
            for (String temporaryMemoryToFileContent : allLines) {
                FileSearchApplication.contentOfFile = temporaryMemoryToFileContent.replaceAll("^a-zA-Z", " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
