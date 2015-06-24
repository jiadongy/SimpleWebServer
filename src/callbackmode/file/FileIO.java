package callbackmode.file;

import callbackmode.ServerEnv;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.util.Properties;

/**
 * Created by Feiyu on 2015/6/22 0022.
 */
public class FileIO {
    static private ServerEnv context = ServerEnv.getInstance();

    static public String getFileContent(File file){
        System.out.println("FileIO:callbackmode.FileManager.useChannel = "
                +context.getConfiguration().get("callbackmode.FileManager.useChannel"));
        if(context.getConfiguration().get("callbackmode.FileManager.useChannel").equals("true"))
            return getFileContentSyncWithChannel(file);
        else
            return getFileContentSyncWithoutChannel(file);
    }

    static private String getFileContentSyncWithoutChannel(File file) {
        String content = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String temp;
            while ((temp = reader.readLine()) != null)
                sb.append(temp);
            content = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return content;
        }
    }

    static private String getFileContentSyncWithChannel(File file){
        String content = null;

        try (FileChannel channel = new FileOutputStream(file).getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(16 * 1024);

            StringBuilder sb = new StringBuilder();
            while(channel.read(buffer) != -1){
                buffer.flip();
                sb.append(buffer.toString());
                buffer.clear();
            }
            content = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return content;
        }
    }


}
