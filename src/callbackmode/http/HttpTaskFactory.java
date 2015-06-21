package callbackmode.http;

import callbackmode.ServerEnv;
import callbackmode.http.bean.HttpBeans;
import callbackmode.http.bean.HttpResponseBean;
import callbackmode.socket.async.SocketCompletionHandler;

import java.io.File;

/**
 * Created by Feiyu on 2015/6/21 0021.
 */
public class HttpTaskFactory {
    private ServerEnv context;

    public HttpTaskFactory(ServerEnv context) {
        this.context = context;
    }

    public Runnable newHttpProcessTask(int socketId, SocketCompletionHandler prevHandler,
                                       File fileToGet, ServerEnv context){
        return new HttpProcessTask(socketId, prevHandler,
                fileToGet, context);
    }

    private class HttpProcessTask implements Runnable {
        private ServerEnv context;
        private SocketCompletionHandler prevHandler;
        private File fileToGet;
        private int socketId;

        public HttpProcessTask(int socketId, SocketCompletionHandler prevHandler,
                               File fileToGet, ServerEnv context) {
            this.socketId = socketId;
            this.context = context;
            this.prevHandler = prevHandler;
            this.fileToGet = fileToGet;
        }

        @Override
        public void run() {
            String content = context.getFileManager().getFileContent(fileToGet);

            HttpResponseBean bean = HttpBeans.createResponseBean(content);
            String output = bean.toString();

            if (bean.getData() != null)
                System.out.println("HttpCompletionHandler:receive File content , prepare to complete SocketCompletionHandler");
            else
                System.out.println("HttpCompletionHandler:receive nothing , prepare to fail SocketCompletionHandler");
            prevHandler.completed(output, socketId);
        }
    }

}
