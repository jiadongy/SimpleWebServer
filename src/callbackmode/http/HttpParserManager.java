package callbackmode.http;

import callbackmode.ServerEnv;
import callbackmode.http.bean.HttpBeans;
import callbackmode.http.bean.HttpRequestBean;
import callbackmode.socket.async.SocketCompletionHandler;

import java.nio.channels.CompletionHandler;

/**
 * Created by Feiyu on 2015/6/21 0021.
 */
public class HttpParserManager {
    private static HttpParserManager ourInstance = new HttpParserManager();

    public static HttpParserManager getInstance() {
        return ourInstance;
    }

    private HttpParserManager() {
    }
    private ServerEnv context = ServerEnv.getInstance();
    private HttpTaskFactory factory = new HttpTaskFactory(context);

    public void executeTask(int socketId, String inputString, SocketCompletionHandler callback) {
        HttpRequestBean bean = HttpBeans.getBeanFromRequestString(inputString);
        Runnable httpTask = factory.newHttpProcessTask(socketId, callback, bean.getUrl(), context);

        context.getThreadPool().submit(httpTask);
    }




}
