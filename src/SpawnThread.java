/* It the child thread which is running in background to call Filesearching class and do the searching operation of file */
import java.sql.SQLException;
import java.util.Scanner;

 class SpawnThread extends Thread{
     /* run() of Thread class is overridden here*/
    public void run(){
    FileSearching filename = new FileSearching(FileSearchApplication.filepath);
        if (FileSearchApplication.contentOfFile != null) {
        System.out.println("got the file");
        Scanner scannerCommandLineInput = new Scanner(System.in);
        System.out.println("enter the word");         ///////asking user to give word
        FileSearchApplication.word = scannerCommandLineInput.nextLine();
            try {
                FileSearchApplication.wordSearch(FileSearchApplication.word);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                FileSearchApplication.errorToDataBase(FileSearchApplication.word);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}}
