import java.io.File;
import java.util.Scanner;

class FileSearching {           ///////////////checking of file avaibility
   protected FileSearching(String file)  {
        if(file.endsWith("txt") || file.endsWith("json")){
        FileChecking(file);
        }
    }

    private static void FileChecking(String filepath){
        try {
            String sn="";
            File f = new File(filepath);
            Scanner s= new Scanner(f);
            while( s.hasNextLine()){
                sn=sn+s.nextLine();
            FileSearchApplication.contentOfFile = sn;
        }
            FileSearchApplication.contentOfFile = FileSearchApplication.contentOfFile.replaceAll("[^a-zA-Z]"," ");

        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}

