package callbackmode.http.bean;

import java.util.Date;

/**
 * Created by Feiyu on 2015/6/21 0021.
 */
public class HttpResponseBean {
    private String version;
    private int code;
    private String message;

    private Date date;
    private String server;
    private String connection;

    private String contentType;
    private int contentLength;

    private String data;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        response.append(getVersion()+" "+getCode()+" "+getMessage()+"\n");
        response.append("Date: "+getDate()+"\n");
        response.append("Server: "+getServer()+"\n");
        response.append("Connection: "+getConnection()+"\n");
        response.append("Content-Type: "+getContentType()+"\n");
        response.append("Content-length: "+getContentLength()+"\n");
        response.append(getData());
        return response.toString();

    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static int HTTP_OK = 200;
    public static int HTTP_NOT_FOUND = 404;
}
