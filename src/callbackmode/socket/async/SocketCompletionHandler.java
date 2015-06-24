package callbackmode.socket.async;

import callbackmode.ServerEnv;
import callbackmode.socket.SocketIO;

import java.io.IOException;
import java.net.Socket;
import java.nio.channels.CompletionHandler;

/**
 * Created by Feiyu on 2015/6/21 0021.
 */
public class SocketCompletionHandler implements CompletionHandler<String,Integer>{
    private SocketManager socketManager;

    public SocketCompletionHandler(ServerEnv context){
        this.socketManager = context.getSocketManager();
    }

    @Override
    public void completed(String result, Integer hashcode) {
        Socket socket = socketManager.getSocket(hashcode);
        if(socket != null){
            try {
                SocketIO.writeToSocket(socket,result);
                socket.close();

                System.out.println("SocketCompletionHandler:Socket "+hashcode+" Result received : "+result);
            } catch (IOException e) {
                socketManager.removeSocket(hashcode);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void failed(Throwable exc, Integer hashcode) {
        try {
            socketManager.getSocket(hashcode).close();
            System.out.println("SocketCompletionHandler:Socket "+hashcode+" Fail to get Result ");
            socketManager.removeSocket(hashcode);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
