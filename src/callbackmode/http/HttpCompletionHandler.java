package callbackmode.http;

import callbackmode.ServerEnv;
import callbackmode.http.bean.HttpBeans;
import callbackmode.http.bean.HttpResponseBean;
import callbackmode.socket.async.SocketCompletionHandler;

import java.nio.channels.CompletionHandler;

/**
 * Created by Feiyu on 2015/6/21 0021.
 */
@Deprecated
public class HttpCompletionHandler implements CompletionHandler<String,Integer> {
    private SocketCompletionHandler prevHandle;
    private ServerEnv context;
    private HttpParserManager httpParserManager;

    public HttpCompletionHandler(SocketCompletionHandler prevHandle, ServerEnv context) {
        this.prevHandle = prevHandle;
        this.context = context;
        this.httpParserManager = context.getHttpParserManager();
    }

    @Override
    public void completed(String result, Integer hashcode) {
        HttpResponseBean bean = HttpBeans.createResponseBean(result);
        String output = bean.toString();

        System.out.println("HttpCompletionHandler:receive File content , prepare to complete SocketCompletionHandler");
        prevHandle.completed(output,hashcode);
    }

    @Override
    public void failed(Throwable exc, Integer hashcode) {
        HttpResponseBean bean = HttpBeans.createResponseBean(null);
        String output = bean.toString();

        System.out.println("HttpCompletionHandler:receive nothing , prepare to fail SocketCompletionHandler");
        prevHandle.completed(output,hashcode);
    }
}
