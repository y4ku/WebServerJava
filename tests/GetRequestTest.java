import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: y4ku
 * Date: 10/18/11
 * Time: 8:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class GetRequestTest {
        Map<String, String> formattedRequest = new HashMap<String,String>();

    @Before
    public void initialize(){
        formattedRequest.put("Method", "GET");
        formattedRequest.put("Request-URI", "/");
        formattedRequest.put("HTTP-Version", "HTTP/1.1");
    }

    @Test
    public void testGetPath(){

        assertEquals("index.html", (new GetRequest()).getPath(formattedRequest));

    }


}
