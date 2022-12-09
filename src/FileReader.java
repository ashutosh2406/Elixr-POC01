import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

class FileReader {           ///////////////checking of file avaibility
    protected FileReader(String file) {
        if (file.endsWith("txt") || file.endsWith("json")) {
            FileChecking(file);
        }
    }

    private static void FileChecking(String filepath) {
        try {
            Path readPath = Path.of(filepath);
            String temporaryMemoryOfFileContent = Files.readString(readPath);
            FileSearchApplication.contentOfFile = temporaryMemoryOfFileContent;
            FileSearchApplication.contentOfFile = FileSearchApplication.contentOfFile.replaceAll("[^a-zA-Z]", " ");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

