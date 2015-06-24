package examples.socket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Feiyu on 2015/6/14 0014.
 */
public class socketWithoutNIO {
    public static void main(String... args)  {
        try(ServerSocket server=new ServerSocket(8888)){
            Socket socket = server.accept();
            InputStreamReader reader=new InputStreamReader(socket.getInputStream());
            char[] buffer=new char[64];
            reader.read(buffer);
            System.out.println(buffer);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
