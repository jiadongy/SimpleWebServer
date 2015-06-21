package callbackmode.file;

import java.io.File;

/**
 * Created by Feiyu on 2015/6/21 0021.
 */
public class FileManager {
    private static FileManager ourInstance = new FileManager();

    public static FileManager getInstance() {
        return ourInstance;
    }

    private FileManager() {
    }

    public String getFileContent(File fileToGet) {
        return FileIO.getFileContent(fileToGet);

    }
}
