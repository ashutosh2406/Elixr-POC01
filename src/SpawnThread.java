/* It the child thread which is running in background to call Filesearching class and do the searching operation of file */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class SpawnThread extends Thread {

    /* run() of Thread class is overridden here*/

    public void run() {
        FileReader.isFileExists(FileSearchApplication.filepath);
        if (FileSearchApplication.contentOfFile != null) {
            System.out.println("got the file");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("enter the word");
                FileSearchApplication.searchedWord = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                FileSearchApplication.isWordExists(FileSearchApplication.searchedWord);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                FileSearchApplication.errorToDataBase(FileSearchApplication.searchedWord);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
