/* It the child thread which is running in background to call Filesearching class and do the searching operation of file */

import java.sql.SQLException;
import java.util.Scanner;

class SpawnThread extends Thread {

    /* run() of Thread class is overridden here*/

    public void run() {
        FileReader filename = new FileReader(FileSearchApplication.filepath);
        if (FileSearchApplication.contentOfFile != null) {
            System.out.println("got the file");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("enter the word");
               FileSearchApplication.word  = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                FileSearchApplication.wordSearching(FileSearchApplication.word);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                FileSearchApplication.errorToDataBase(FileSearchApplication.word);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
