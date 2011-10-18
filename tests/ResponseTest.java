import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: y4ku
 * Date: 10/17/11
 * Time: 10:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseTest {
    Map<String, String> formattedRequest = new HashMap<String,String>();

    @Before
    public void initialize(){
        formattedRequest.put("Method", "GET");
        formattedRequest.put("Request-URI", "/");
        formattedRequest.put("HTTP-Version", "HTTP/1.1");
    }
    @Test
    public void testCheckResponseType(){
        Response response = new Response(formattedRequest);

        ResponseType type = response.checkResponseType();
        assertEquals(type.getClass().getName(), "GetRequest");
    }
}
