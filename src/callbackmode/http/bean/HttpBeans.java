package callbackmode.http.bean;

import callbackmode.ServerEnv;
import callbackmode.http.bean.HttpRequestBean;
import callbackmode.http.bean.HttpResponseBean;

import java.io.File;
import java.util.Date;

/**
 * Created by Feiyu on 2015/6/21 0021.
 */
public class HttpBeans {

    public static File getFileFromURL(String url){
        File file = null;
        if(url.indexOf('/') == 0){
            file = new File(ServerEnv.getInstance().getRootDir()+url);
        }
        return file;
    }

    public static HttpRequestBean getBeanFromRequestString(String s){
        HttpRequestBean bean = null;
        String[] lines = s.split("\n");
        if(lines.length > 0){
            bean = new HttpRequestBean();
            String[] words = lines[0].split(" ");
            if(words.length >= 2) {
                bean.setMethod(words[0]);
                bean.setUrl(getFileFromURL(words[1]));
                bean.setVersion(words[2]);
            }
        }
        return bean;
    }

    public static HttpResponseBean createResponseBean(String data){
        HttpResponseBean bean = new HttpResponseBean();
        bean.setData(data);
        if(data != null) {
            bean.setCode(HttpResponseBean.HTTP_OK);
            bean.setMessage("OK");
            bean.setContentLength(data.length());
        }
        else {
            bean.setCode(HttpResponseBean.HTTP_NOT_FOUND);
            bean.setMessage("NOT FOUND");
            bean.setContentLength(0);
        }
        bean.setDate(new Date());
        bean.setContentType("text/html");
        bean.setConnection("closed");
        bean.setServer("simpleWebServer");
        bean.setVersion("HTTP/1.1");
        return bean;
    }

}
