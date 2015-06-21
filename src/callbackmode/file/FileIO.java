package callbackmode.file;

import callbackmode.ServerEnv;

import java.io.*;

/**
 * Created by Feiyu on 2015/6/22 0022.
 */
public class FileIO {
    static private ServerEnv context = ServerEnv.getInstance();

    static public String getFileContent(File file){
        return getFileContentSyncWithChannel(file);
    }

    static private String getFileContentSyncWithChannel(File file) {
        String content = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String temp;
            while ((temp = reader.readLine()) != null)
                sb.append(temp);
            content = sb.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return content;
        }
    }

}
