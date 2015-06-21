package callbackmode;

import callbackmode.file.FileManager;
import callbackmode.http.HttpParserManager;
import callbackmode.socket.async.SocketManager;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Feiyu on 2015/6/21 0021.
 */
public class ServerEnv {
    private static ServerEnv ourInstance = new ServerEnv();

    public static ServerEnv getInstance() {
        return ourInstance;
    }

    private ServerEnv() {
    }
    private ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);

    private SocketManager socketManager = SocketManager.getInstance();
    private HttpParserManager httpParserManager = HttpParserManager.getInstance();
    private FileManager fileManager = FileManager.getInstance();

    private String rootDir = "E:\\Workspace\\SimpleWebServer\\WebRoot";

    public static void main(String... args){
        try {
            ServerSocket server = new ServerSocket(8888);
            while(true){
                Socket socket = server.accept();
                System.out.println("ServerEnv: receive new accept : "+socket.getRemoteSocketAddress().toString());
                getInstance().getSocketManager().executeTask(socket);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SocketManager getSocketManager() {
        return socketManager;
    }

    public HttpParserManager getHttpParserManager() {
        return httpParserManager;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public ExecutorService getThreadPool() {
        return threadPool;
    }

    public String getRootDir() {
        return rootDir;
    }
}
