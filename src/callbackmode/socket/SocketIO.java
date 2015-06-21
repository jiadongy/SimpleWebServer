package callbackmode.socket;

import callbackmode.ServerEnv;

import java.io.*;
import java.net.Socket;

/**
 * Created by Feiyu on 2015/6/20 0020.
 */
public class SocketIO {
    static private ServerEnv context = ServerEnv.getInstance();

    static public String readFromSocket(Socket socket) throws IOException {
        return readFromSocketWithoutChannel(socket);
    }
    static  public void writeToSocket(Socket socket,String content) throws IOException {
        writeToSocketWithoutChannel(socket,content);
    }

    static private String readFromSocketWithoutChannel(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String temp;
        while((temp = reader.readLine()) != null)
            sb.append(temp);
        return sb.toString();
    }

    static private void writeToSocketWithoutChannel(Socket socket,String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write(content);
        writer.flush();
    }
}
