package callbackmode.socket.async;


import callbackmode.ServerEnv;
import callbackmode.socket.SocketIO;

import java.io.IOException;
import java.net.Socket;
import java.util.Observable;

/**
 * Created by Feiyu on 2015/6/20 0020.
 */
public class SocketTaskFactory {


    private final ServerEnv context;
    private SocketCompletionHandler completionHandler;

    public SocketTaskFactory(ServerEnv context) {
        this.context = context;
        this.completionHandler = new SocketCompletionHandler(this.context);
    }

    public Runnable newSocketProcessTask(Socket socket){
        return new SocketProcessTask(socket,this.completionHandler,this.context);
    }




    protected class SocketProcessTask implements Runnable{
        private Socket socket;
        private SocketCompletionHandler handler;
        private ServerEnv context;
        private String inputString;

        public SocketProcessTask(Socket socket, SocketCompletionHandler handler, ServerEnv context) {
            this.socket = socket;
            this.handler = handler;
            this.context = context;
        }

        @Override
        public void run() {
            try {
                inputString = SocketIO.readFromSocket(this.socket);
                context.getHttpParserManager().executeTask(socket.hashCode(), inputString, handler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    protected class OutputHandle extends Observable implements Runnable{

        @Override
        public void run() {

        }
    }
}
