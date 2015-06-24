package examples.socket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Feiyu on 2015/6/14 0014.
 */
public class socketRebindWithoutNIO {
    public static void main(String... args)  {
        try(ServerSocket server=new ServerSocket(8888)){
            Socket socket = server.accept();
            InputStreamReader reader=new InputStreamReader(socket.getInputStream());
            char[] buffer=new char[64];
            reader.read(buffer);
            System.out.println(buffer);
            reader.close();
            socket.close();
            if(socket.isClosed()){
                socket.bind(new InetSocketAddress(9999));
                /*java.net.SocketException: Socket is closed
                at java.net.Socket.bind(Socket.java:612)
                at examples.socket.socketRebindWithoutNIO.main(socketRebindWithoutNIO.java:23)*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
