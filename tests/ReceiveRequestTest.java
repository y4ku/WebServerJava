
import org.junit.Test;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: y4ku
 * Date: 10/17/11
 * Time: 10:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReceiveRequestTest {
   BufferedReader in;

    @Test
    public void testGetRequest(){
        this.in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("GET / HTTP/1.1".getBytes())));
        ReceiveRequest request = new ReceiveRequest(in);
        assertEquals(request.getRequest(), "GET / HTTP/1.1");
    }

    @Test
    public void testParseRequest(){
        String requested = "GET / HTTP/1.1";
        ReceiveRequest request = new ReceiveRequest(in);
        Map formatted = request.parseRequest(requested);
        assertEquals(formatted.get("Method"), "GET");
        assertEquals(formatted.get("Request-URI"), "/");
        assertEquals(formatted.get("HTTP-Version"), "HTTP/1.1");
    }

    @Test
    public void testParams(){
        String requested = "GET /game?move=1&player=2 HTTP/1.1";
        ReceiveRequest request = new ReceiveRequest(in);
        Map formatted = request.parseRequest(requested);
        assertEquals(formatted.get("Method"), "GET");
        assertEquals(formatted.get("Request-URI"), "/game");
        assertEquals(formatted.get("param0"), "1");
        assertEquals(formatted.get("param1"), "2");
        assertEquals(formatted.get("HTTP-Version"), "HTTP/1.1");

    }

}
