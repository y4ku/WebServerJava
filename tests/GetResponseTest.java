import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: y4ku
 * Date: 10/18/11
 * Time: 8:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class GetResponseTest {
        Map<String, String> index = new HashMap<String,String>();
        Map<String, String> randomPath = new HashMap<String,String>();
        String home;
        String notFound;

    @Before
    public void initialize(){
        index.put("Method", "GET");
        index.put("Request-URI", "/");
        index.put("HTTP-Version", "HTTP/1.1");

        randomPath.put("Method", "GET");
        randomPath.put("Request-URI", "/thisispath/wooh/moose");
        randomPath.put("HTTP-Version", "HTTP/1.1");

        home = "HTTP/1.1 200 OK\r\nContent-Type: text/html; charset=UTF-8\r\n\r\n"
                +"<html><head><title>Home Page</title></head><body><h1> This is a Home Page! </h1></body></html>";

        notFound = "HTTP/1.1 404 Not Found\r\n" +
                "Content-Type: text/html; charset=UTF-8\r\n" +
                "\r\n" +
                "<html><body><h1>File Not Found</h1></body></html>";

    }

    @Test
    public void testGetPath(){

        assertEquals("index.html", (new GetResponse()).getPath(index));
        assertEquals("/thisispath/wooh/moose", (new GetResponse()).getPath(randomPath));

    }

    @Test
    public void badFileTest(){
        String assertMe = new String((new GetResponse()).serveResponse(randomPath));
        assertEquals(notFound, assertMe);
    }

    @Test
    public void testServeResponse(){
        String assertMe = new String((new GetResponse()).serveResponse(index));
        assertEquals(home, assertMe);

    }



}
