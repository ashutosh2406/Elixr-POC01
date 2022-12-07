/*It is the main class where I am taking the filepath as an input from user and checking the presence of seached word*/
import java.sql.SQLException;
import java.util.Scanner;

public class FileSearchApplication{
  protected static String contentOfFile,filepath,word;
  protected static int totalNoOfWordsInFile;
  /*main method where execution starts and here I am taking the file path input from user
  * filepath= C:\Users\ashutosh.palai\Documents\hello.txt
  * jsonfile=C:\Users\ashutosh.palai\Downloads\example_1.json */
    public static void main(String[] args) {
        Scanner scannerCommandLineInputForPath=new Scanner(System.in);
        System.out.println("enter file path");
        filepath=scannerCommandLineInputForPath.next();
        System.out.println("Processing....");
        SpawnThread ch=new SpawnThread();
        ch.run();
    }

    /* checking the presence of the searched word and counting its repetation in file and also counting the total no of words in file */
    protected static void wordSearch(String word) throws SQLException {
         String temporaryMemoryForWords=""; int countingNoOfTimesTheWordIsPresent=1,countingNoOfTimesTheWordIsrepetating=0,comparingSearchedWordCharacters=0;;
        char[]charactersOfGivenWord=word.toCharArray();
        for (int i = 0; i < contentOfFile.length(); i++) {
            char stringFileContentToCharacter=contentOfFile.charAt(i);
            int a=stringFileContentToCharacter;
                temporaryMemoryForWords=temporaryMemoryForWords+stringFileContentToCharacter;
                if(stringFileContentToCharacter==' ') {
                    String eachSpecificWordsOfFile = temporaryMemoryForWords;
                    totalNoOfWordsInFile++;
                    char[] ch2 = eachSpecificWordsOfFile.toCharArray();
                    if (word.length() >= eachSpecificWordsOfFile.length()) {

                        for (int j = 0; j < eachSpecificWordsOfFile.length(); j++) {
                            if (ch2[j] == charactersOfGivenWord[j]) {
                                comparingSearchedWordCharacters++;
                            } else {
                                comparingSearchedWordCharacters = 0;
                            }
                        }
                    } else if (word.length() <= eachSpecificWordsOfFile.length()) {
                        for (int j = 0; j < word.length(); j++) {
                            if (ch2[j] == charactersOfGivenWord[j]) {
                                comparingSearchedWordCharacters++;
                            } else {
                                comparingSearchedWordCharacters = 0;
                            }
                        }
                    }temporaryMemoryForWords="";
                }
            if(comparingSearchedWordCharacters==word.length()){
                countingNoOfTimesTheWordIsPresent++;
                countingNoOfTimesTheWordIsrepetating++;
                comparingSearchedWordCharacters=0;
            }
        }
        FileSearchApplication.totalNoOfWordsInFile=totalNoOfWordsInFile;
        if(countingNoOfTimesTheWordIsPresent>1){
            System.out.println("got the word.The word '"+word+"' is present "+(countingNoOfTimesTheWordIsPresent-1)+" times in the file and it is repetating for "+(countingNoOfTimesTheWordIsrepetating-1)+" times inside the file content");
            DataBaseHelper db=new DataBaseHelper(word,filepath,totalNoOfWordsInFile,"");
        }else{
            String errorMessage="word not found";
            System.out.println("word not found");
            DataBaseHelper db=new DataBaseHelper(word,filepath, totalNoOfWordsInFile,errorMessage);
        }
    }

    /* Sending some error message to database if the file path given by the user is invalid */
    protected static void errorToDataBase(String word) throws SQLException {
        String errorMessage="invalid File Path";
        System.out.println("The file is not found");
        DataBaseHelper db=new DataBaseHelper(word,filepath,totalNoOfWordsInFile,errorMessage);
    }
}
