package test.callbackmode.http.bean;

import callbackmode.ServerEnv;
import callbackmode.http.bean.HttpBeans;
import callbackmode.http.bean.HttpRequestBean;
import callbackmode.http.bean.HttpResponseBean;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * HttpBeans Tester.
 *
 * @author <Authors name>
 * @since <pre>06/22/2015</pre>
 * @version 1.0
 */
public class HttpBeansTest extends TestCase {
    public HttpBeansTest(String name) {
        super(name);
    }

    private ServerEnv serverEnv;
    public void setUp() throws Exception {
        serverEnv = ServerEnv.getInstance();
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetFileFromURL() throws Exception {
        //TODO: Test goes here...
    }

    public void testGetBeanFromRequestString() throws Exception {
        String request = "GET /index.html HTTP/1.1\n" +
                "Host: localhost\n" +
                "Connection: keep-alive\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36 LBBROWSER\n" +
                "Accept-Encoding: gzip, deflate, sdch\n";
        HttpRequestBean bean = HttpBeans.getBeanFromRequestString(request);
        assertEquals(bean.getMethod(), "GET");
        assertEquals(bean.getUrl().getName(), "index.html");
        //assertEquals(bean.getHost(),"localhost");//还没实现
    }

    public void testCreateResponseBean() throws Exception {
        String out = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head lang=\"en\">\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>Welcome to My Blog</p>\n" +
                "</body>\n" +
                "</html>";
        HttpResponseBean bean = HttpBeans.createResponseBean(out);
        assertEquals(bean.getMessage(),"OK");
        assertEquals(bean.getCode(),200);
        assertEquals(bean.getData(),out);
        assertEquals(bean.getContentLength(),out.length());
        System.out.print(bean.toString());
    }


    public static Test suite() {
        return new TestSuite(HttpBeansTest.class);
    }
}
