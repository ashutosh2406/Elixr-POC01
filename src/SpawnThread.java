/* It the child thread which is running in background to call Filesearching class and do the searching operation of file */

class SpawnThread extends Thread {

    /* run() of Thread class is overridden here*/

    public void run() {
        FileReader.isFileExists();
        if (FileSearchApplication.contentOfFile != null) {
            System.out.println("got the file");
            try {
                FileSearchApplication.isWordExists();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                FileSearchApplication.errorToDataBase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
