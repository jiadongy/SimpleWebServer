package callbackmode;

import callbackmode.file.FileManager;
import callbackmode.http.HttpParserManager;
import callbackmode.socket.async.SocketManager;
import util.Configuration;

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

    //6.22
    //之前下面没有static，也就是说在实例化的时候才会执行，但是SocketManager实例化的时候会用到ServerEnv的实例，
    //但这个时候他的实例还在new当中，所以为null，导致SocketManager实例化时抛出NUllPoint
    //JVM抛出ExceptionInInitializerError
    private static ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);

    private static SocketManager socketManager = SocketManager.getInstance();
    private static HttpParserManager httpParserManager = HttpParserManager.getInstance();
    private static FileManager fileManager = FileManager.getInstance();

    private static String rootDir = "E:\\Workspace\\SimpleWebServer\\WebRoot";

    private static Configuration configuration = new Configuration("E:\\Workspace\\SimpleWebServer\\conf\\server_default.conf");

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

    public Configuration getConfiguration() {
        return configuration;
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
