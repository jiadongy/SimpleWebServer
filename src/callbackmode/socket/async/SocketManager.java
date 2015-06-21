package callbackmode.socket.async;

import callbackmode.ServerEnv;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Feiyu on 2015/6/21 0021.
 */
public class SocketManager {
    private static SocketManager ourInstance = new SocketManager();

    public static SocketManager getInstance() {
        return ourInstance;
    }

    private SocketManager() {
    }
    private ServerEnv context = ServerEnv.getInstance();
    private SocketTaskFactory factory = new SocketTaskFactory(context);
    private Map<Integer,Socket> socketHashMap = new HashMap<>();

    public void executeTask(Socket socket){
        socketHashMap.put(socket.hashCode(),socket);
        Runnable task = factory.newSocketProcessTask(socket);
        context.getThreadPool().submit(task);

        System.out.println("SocketManager:Socket "+socket.hashCode()+" Processing Task has been submitted");

    }

    public Socket getSocket(Integer hashcode) {
        Socket socket = this.socketHashMap.get(hashcode);
        return socket;
    }

    public void removeSocket(Integer hashcode) {
        this.socketHashMap.remove(hashcode);
    }
}
